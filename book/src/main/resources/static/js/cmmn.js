jQuery(function(){
	jQuery.get('/cmmn/layout/cmmn-themes.html', function(data) {
	   $('#cmmn-themes').html(data)
	})
	jQuery.get('/cmmn/layout/menu-mobile.html', function(data) {
	   $('#menu-mobile').html(data)
	})
	jQuery.get('/cmmn/layout/top-bar.html', function(data) {
	  // $('#top-bar').html(data)
	})
	 
	
	
	
	
	/**
		 $(".search").find("input").each(function() {
      $(this).on("focus", function() {
        $(".search-result").addClass("show")
      })
      $(this).on("focusout", function() {
        $(".search-result").removeClass("show")
      })
    }) */
})
 