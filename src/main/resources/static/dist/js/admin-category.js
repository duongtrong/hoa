$(document).ready(function() {
    $("#btn-category-create").on("click", function () {
        if($('#txt-category-create').val().trim() === '' || $('#txt-category-create').val().trim().length < 4 || $('#txt-category-create').val().trim().length > 30) {
            alert("Vui lòng nhập tên categogy trong khoảng 4 đến 30 ký tự!");
            return;
        }

        var data = {
            name: $('#txt-category-create').val().trim()
        }

        axios.post('/categories/create', data).then(function(res) {
            if(res) {
                location.replace("/categories")
            } else  {
                alert("Đã có lỗi xảy ra!")
            }
        });
    });

    $("#btn-category-update").on("click", function () {
        if($('#txt-category-update').val().trim() === '' || $('#txt-category-update').val().trim().length < 4 || $('#txt-category-update').val().trim().length > 30) {
            alert("Vui lòng nhập tên categogy trong khoảng 4 đến 30 ký tự!");
            return;
        }

        var id = $('#txt-category-update').attr('cateID');

        var data = {
            name: $('#txt-category-update').val().trim()
        }

        axios.post('/categories/edit/' + id, data).then(function(res) {
            if(res) {
                location.replace("/categories")
            } else  {
                alert("Đã có lỗi xảy ra!")
            }
        });
    });
});
