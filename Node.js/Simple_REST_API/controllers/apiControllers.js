exports.test = function (req, res) {
    res.json({message: 'test successfully executed'});
}

exports.serve = function (req, res) {
    res.sendFile(__dirname + '/index.html');
}