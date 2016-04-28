/*
 Slidemenu
 */
(function () {
    var  $menu_trigger = $(".menu-trigger");

    if (typeof $menu_trigger !== 'undefined') {
        $menu_trigger.bind('click', function () {
            $body.className = ($body.className == 'menu-active') ? '' : 'menu-active';
        });
    }

}).call(this);