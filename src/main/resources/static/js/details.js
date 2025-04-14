let currentPage = 0;

function loadDetails(page) {

    const type = $("#type").val() || ""
    const device = $("#device").val() || ""
    const country = $("#country").val() || ""
    const name = $("#name").val() || ""

    const url = "/details?page=" + page +
        "&size=5" +
        "&sort=id,asc" +
        "&type_id=" + encodeURIComponent(type) +
        "&device_id=" + encodeURIComponent(device) +
        "&country_id=" + encodeURIComponent(country) +
        "&name=" + encodeURIComponent(name)

    $.get({
        url: url,
        contentType: "application/json",
        dataType: "json",
        success: function (data, status, xhr) {
            $("#filterFormError").hide()
            const listContainer = $("#detailsList");
            listContainer.show()
            listContainer.empty(); // Очистить список перед добавлением новых данных

            data.content.forEach(detail => {
                listContainer.append(`
                    <li>
                        ${detail.name} 
                        <button onclick="showDetail(${detail.id})">Подробнее</button>
                        <button onclick="showUpdateForm(${detail.id})">обновить</button>
                        <button onclick="deleteDetail(${detail.id})">удалить</button>
                    </li>
                `);
            });
            currentPage = page;
            $("#pageNumber").text("Страница: " + (currentPage + 1));
            togglePaginationButtons(data.first,data.last);
        },
        error: function (xhr) {
            $("#filterFormError").text(xhr.responseJSON.message).show()
            $("#detailsList").hide()
    }
    });
}

function deleteDetail (detailId) {
    $.ajax({
        url: "/details/" + detailId,
        type: "DELETE",
        success: function () {
            loadDetails(currentPage)
            closeModal('detailModal')
        }
    })
}

function togglePaginationButtons(first, last) {
    if (!first) {
        $("#prevPage").show()
    } else {
        $("#prevPage").hide()
    }
    if (!last) {
        $("#nextPage").show()
    } else {
        $("#nextPage").hide()
    }
}

function showUpdateForm(detailId) {
    $(".detailDevice").empty();
    $(".detailType").empty();
    $(".detailCountry").empty();
    openModal('editModal')
    $.get({
        url: "/details/" + detailId,
        success: function (data) {
            $("#detailName").val(data.name)
            $("#detailType").append(`<option value="${data.type.id}">${data.type.name}</option>`)
            $("#detailDevice").append(`<option value="${data.device.id}">${data.device.brand.name} ${data.device.model}</option>`)
            $("#detailCountry").append(`<option value="${data.country.id}">${data.country.name}</option>`)
            $("#detailAmount").val(data.amount)
        }
    })
    $("#editForm").submit(function (e) {
        e.preventDefault()
        const updateData = {
            name : $("#detailName").val(),
            type : {
                id : $("#detailType").val()
            },
            device : {
                id : $("#detailDevice").val()
            },
            country : {
                id : $("#detailCountry").val()
            },
            amount: $("#detailAmount").val()
        }
        $.ajax({
            url: "/details/" + detailId,
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(updateData),
            success: function (response) {
                closeModal('editModal')
                showDetail(response.id)
                loadDetails(currentPage)
            },
            error: function (xhr) {
                $("#editFormError").text(xhr.responseJSON.message + ": detail with this name is already exists").show()
            }
        })
    })
}

function showCreateForm() {
    openModal('createModal')
    $("#createForm").submit(function (e) {
        e.preventDefault()
        const createData= {
            name : $("#newDetailName").val(),
            type : {
                id : $("#newDetailType").val()
            },
            device : {
                id : $("#newDetailDevice").val()
            },
            country : {
                id : $("#newDetailCountry").val()
            },
            amount: $("#newDetailAmount").val()
        }
        $.post({
            url: "/details",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function (response) {
                closeModal('createModal')
                showDetail(response.id)
                loadDetails(currentPage)
            },
            error: function (xhr) {
                $("#createFormError").text(xhr.responseJSON.message + ": detail with this name is already exists").show()
            }
        })
    })
}

function showDevices() {
    let optionCount = $(".detailDevice option").length;
    if (optionCount===1 || optionCount===0){
    $.get({
        url: "/devices",
        success: function (data) {
            data.content.forEach(function(device) {
                $(".detailDevice").append(`<option value="${device.id}">${device.brand.name} ${device.model}</option>`);
            });
        }
    })}
}

function showTypes() {
    let optionCount = $(".detailType option").length;
    if (optionCount===1 || optionCount===0){
    $.get({
        url: "/types",
        success: function (data) {
            data.content.forEach(function(type) {
                $(".detailType").append(`<option value="${type.id}">${type.name}</option>`);
            });
        }
    })}
}

function showCountries() {
    let optionCount = $(".detailCountry option").length;
    if (optionCount===1 || optionCount===0){
    $.get({
        url: "/countries",
        success: function (data) {
            data.content.forEach(function(country) {
                $(".detailCountry").append(`<option value="${country.id}">${country.name}</option>`);
            });
        }
    })}
}

function showDetail(detailId) {
    $.get({
        url: "/details/" + detailId,
        success: function (data) {
            $("#detailInfo").html(`
                Название: ${data.name}
                Категория: ${data.type.name}
                Количество: ${data.amount}
                Для устройства: ${data.device.brand.name} ${data.device.model}
                Страна производитель: ${data.country.name}
                <button onclick="showUpdateForm(${data.id})">обновить</button>
                <button onclick="deleteDetail(${data.id})">удалить</button>
            `);
            openModal('detailModal')
        }
    });
}

function closeModal(modalId) {
   $("#"+modalId).fadeOut();
}

function openModal(modalId) {
    $("#" + modalId).fadeIn();
}

$(document).ready(function () {
    loadDetails(currentPage)

    $("#prevPage").click(function () {
        if (currentPage > 0) {
            loadDetails(currentPage - 1);
        }
    })

    $("#nextPage").click(function () {
        if (currentPage >= 0) {
            loadDetails(currentPage + 1);
        }
    })

    $(".detailType").focus(function (){showTypes()})
    $(".detailDevice").focus(function (){showDevices()})
    $(".detailCountry").focus(function (){showCountries()})


    $("#addingButton").click(function (){
        showCreateForm()
    })

    $("#filterForm select").change(function () {
        currentPage = 0
        loadDetails(currentPage)
    })

    let nameInputTimeout;
    $("#name").on("input", function () {
        clearTimeout(nameInputTimeout);
        nameInputTimeout = setTimeout(() => {
            currentPage = 0;
            loadDetails(currentPage);
        }, 300); // задержка 300мс после последнего ввода
    });
});
