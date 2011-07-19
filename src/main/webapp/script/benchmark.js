function bindPutForm() {
    $('#put').submit(function(event) {
        event.preventDefault();
        var $form = $(this),
                id = $form.find('input[name="id"]').val(),
                shortStringAttribute = $form.find('input[name="shortStringAttribute"]').val(),
                longStringAttribute = $form.find('input[name="longStringAttribute"]').val(),
                intNumber = $form.find('input[name="intNumber"]').val(),
                trueOrFalse = $form.find('input[name="trueOrFalse"]').val(),
                url = $form.attr('action');
        method = 'POST';
        url = url + "/" + id;
        $.ajax({
            url: url,
            type: method,
            data: '{"id":"' + id + '","shortStringAttribute":"' + shortStringAttribute + '","longStringAttribute":"' + longStringAttribute + '","intNumber":"' + intNumber + '","trueOrFalse":"' + trueOrFalse + '"}',
            dataType: 'json',
            cache: false,
            contentType: "application/json; charset=utf-8",
            success:function(res) {
                window.location.href = 'index.html';
            }
        });
    });
}