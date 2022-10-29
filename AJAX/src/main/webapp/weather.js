$(document).ready(function () {
    $("#getWeatherReport").click(function () {
        $cityName = document.getElementById("cityName").value;
        $.post("WeatherJquery",
            {cityName: $cityName},
            function (xml) {
                $("#weatherReport").html($("report", xml).text());
            });
    });
});