$(function() {
	$("span.pie").peity("pie", {
		fill : [ "#1ab394", "#d7d7d7", "#ffffff" ]
	}), $(".line").peity("line", {
		fill : "#1ab394",
		stroke : "#169c81"
	}), $(".bar").peity("bar", {
		fill : [ "#1ab394", "#d7d7d7" ]
	}), $(".bar_dashboard").peity("bar", {
		fill : [ "#1ab394", "#d7d7d7" ],
		width : 100
	});
	var i = $(".updating-chart").peity("line", {
		fill : "#1ab394",
		stroke : "#169c81",
		width : 64
	});
	setInterval(function() {
		var t = Math.round(10 * Math.random()), a = i.text().split(",");
		a.shift(), a.push(t), i.text(a.join(",")).change()
	}, 1e3)
});
