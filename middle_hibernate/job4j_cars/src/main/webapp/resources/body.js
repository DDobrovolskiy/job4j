loadCategoryBody()

function loadCategoryBody() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car/models/bodies',
        dataType: 'json'
    }).done(function (data) {
        console.log(data)
        let html = '<select class="form-control" name="bodyId" id="body">'
        for(let ctg of data)
        {
            console.log(ctg)
            html += '<option value="' + ctg.id + '">'
                html += ctg.type
            html += '</option>'   
        }
        html += '</select>'
        $('#body').replaceWith(html)
        console.log('#body')
    }).fail(function (err) {
        console.log(err)
    })
}