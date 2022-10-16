from . import app
from markupsafe import escape
from flask import render_template, request


@app.route('/')
@app.route('/index')
def index():
    return "Hello, World!"


@app.route('/<name>')
def hello(name):
    return f"Hello, {escape(name)}!"


@app.route('/hello/')
@app.route('/hello/<name>')
def hello_template(name=None):
    return render_template('hello_template.html', name=name)


@app.route('/subscribe-Get', methods=['GET'])
def GET():
    query_string = str(request.query_string)
    return 'Here is the query string: ' + query_string, 200


@app.route('/subscribe-Post', methods=['POST'])
def POST():
    return request.form, 200
