module.exports = function (app) {
    var controllers = require('../controllers/apiControllers');

    // Routes
    app.route("/v1/test")
        .get(controllers.test);

    app.route("/v1/serve")
        .get(controllers.serve);
}