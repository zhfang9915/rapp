


    jQuery('#customer .qqkf').hover(function() {
        jQuery(this).addClass('hover').find('.ly-side-main').animate({
            right: '50px',
            opacity: '1'
        },
        200).show();
    },
    function() {
        jQuery(this).removeClass('hover').find('.ly-side-main').animate({
            right: '70px',
            opacity: '0'
        },
        200,
        function() {
            jQuery(this).hide();
        })
    });

