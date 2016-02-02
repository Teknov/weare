$(document).ready(function() {
    $('#teknovHomepage').fullpage({
		anchors: ['home', 'services', 'aboutUs', 'todo'], 
		afterLoad: function (anchorLink, index) {
			$(".nav").find(".active").removeClass("active");
			$('li[data-menuanchor="'+ anchorLink + '"]').addClass("active");
    }
	});
	
	//for navigation active class
	$(".nav a").on("click", function(){
		$(".nav").find(".active").removeClass("active");
		$(this).parent().addClass("active");
	});
	
	
});