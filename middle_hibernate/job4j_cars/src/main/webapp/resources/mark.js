loadCategoryMark()

function loadCategoryMark() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car/models/marks',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<select class="form-control" name="markId" id="mark">'
        for(let ctg of data)
        {
            console.log(ctg)
            html += '<option value="' + ctg.id + '">'
                html += ctg.name
            html += '</option>'   
        }
        html += '</select>'
        $('#mark').replaceWith(html)
        console.log('#mark')
    }).fail(function (err) {
        console.log(err)
    })
}