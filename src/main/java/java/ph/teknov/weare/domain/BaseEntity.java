package ph.teknov.weare.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import ph.teknov.weare.domain.annotation.VersionOrdered;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Kenneth Mandawe
 *         Date: 1/20/2016
 *         Time: 9:09 PM
 */
@MappedSuperclass
public abstract class BaseEntity implements Identifiable<Long>, Serializable {
    public static final Integer DEFAULT_VERSION = Integer.valueOf(0);
    @Id
    @GeneratedValue(
            strategy = GenerationType.TABLE,
            generator = "teknov-tbl-generator"
    )
    @TableGenerator(
            name = "teknov-tbl-generator",
            table = "teknov_sequence",
            pkColumnName = "entity_name",
            valueColumnName = "next_hi_value",
            allocationSize = 32767
    )
    private Long id;
    @Version
    @Column(
            nullable = false
    )
    private Integer version;
    @Column(
            nullable = false
    )
    private String lastModifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(
            nullable = false
    )
    private Date lastModified;
    private transient boolean stale;

    public BaseEntity() {
        this.version = DEFAULT_VERSION;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getVersion() {
        return this.version;
    }

    public void setVersion(Integer version) {
        if(this.isStaleVersion(version)) {
            this.stale = true;
        }

        this.version = version;
    }

    private boolean isStaleVersion(Integer newVersion) {
        return !this.getClass().isAnnotationPresent(VersionOrdered.class)?false: ObjectUtils.compare(this.version, newVersion) > 0;
    }

    public boolean equals(Object o) {
        if(o instanceof BaseEntity) {
            BaseEntity other = (BaseEntity)o;
            return other.getId() == null && this.id == null?other == this:(new EqualsBuilder()).append(this.id, other.id).isEquals();
        } else {
            return false;
        }
    }

    public int hashCode() {
        return (new HashCodeBuilder()).append(this.id).toHashCode();
    }

    public String toString() {
        return String.format("%s[id=%d]", new Object[]{this.getClass().getSimpleName(), this.getId()});
    }

    @PrePersist
    private void initEntity() {
        this.setVersion(DEFAULT_VERSION);
        this.preUpdateRemove();
    }

    @PreRemove
    @PreUpdate
    private void preUpdateRemove() {
        this.setLastModified(new Date());
    }

    public String getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    private void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModified() {
        return this.lastModified;
    }

    private void setLastModified(Date lastModifiedTimestamp) {
        this.lastModified = lastModifiedTimestamp;
    }

    public static String tableName(Class<? extends BaseEntity> c) {
        Table a = (Table)c.getAnnotation(Table.class);
        return a == null?c.getSimpleName().replaceAll(".class$", "").toLowerCase():a.name();
    }

    public boolean isStale() {
        return this.stale;
    }

    public static class STRLEN {
        public static final int SMALL = 64;
        public static final int DEFAULT = 255;
        public static final int _1K = 1024;
        public static final int _2K = 2048;
        public static final String TEXT_TYPE = "org.hibernate.type.TextType";

        public STRLEN() {
        }
    }
}
