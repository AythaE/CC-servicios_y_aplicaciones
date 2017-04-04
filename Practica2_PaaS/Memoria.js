exports.header = {
    height: "1cm",
    contents: function(pageNum, numPages) {
        var today = new Date();
        var dd = today.getDate();
        var mm = today.getMonth() + 1; //January is 0!
        var yyyy = today.getFullYear();

        if (dd < 10) {
            dd = '0' + dd
        }

        if (mm < 10) {
            mm = '0' + mm
        }

        today = dd + '/' + mm + '/' + yyyy;
        if (pageNum > 1) {
            return "<p style=\"font-size: x-small; color:Grey;\">Aythami Estévez Olivas <span style='float:right'>" + today + "</span></p>"
        } else {
            return
        }
    }
};
exports.footer = {
    height: "1cm",
    contents: function(pageNum, numPages) {
        if (pageNum > 1) {
            return "<p style=\"font-size: x-small; color:Grey;\">Cloud Computing: Servicios y Aplicaciones<span style='float:right'>" +
                pageNum + " / " + numPages + "</span></p>"
        } else {
            return
        }

    }
};
