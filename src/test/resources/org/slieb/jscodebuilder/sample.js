goog.provide('something');
goog.require('something_require');
goog.require('goog.string');

/**
 * does nothing.
 */
something = function () {
    var node = "";
    alert(goog.string.isEmpty(node));
};
