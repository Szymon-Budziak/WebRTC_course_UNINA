$(document).ready(function () {
    alert("I am alive!");
    setTimeout("show()", 1000);
})

function show() {
    $("#table").fadeIn("slow");
    setTimeout("changeHeaders()", 2000);
}

function changeHeaders() {
    $("#table th").css("color", "red");
    setTimeout("changeFirstRow()", 2000);
}

function changeFirstRow() {
    $("#table tr:first").next().css("color", "blue");
    setTimeout("changeOddColumns()", 2000);
}

function changeOddColumns() {
    $("#table tr td:nth-child(odd)").css("background", "green");
    setTimeout("hide()", 2000);
}

function hide() {
    alert("See you soon ...");
    $("#table").fadeOut("slow");
}