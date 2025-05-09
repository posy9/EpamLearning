let currentPage = 0;

$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
});

function loadDetails(page) {
    const rawSort = $("#sortAmount").val();
    const sortAmount = rawSort === "asc" || rawSort === "desc" ? rawSort : "";
    const type = $("#filterType").val() || "";
    const device = $("#filterDevice").val() || "";
    const country = $("#filterCountry").val() || "";
    const name = $("#filterName").val() || "";

    let url = "/details?page=" + page +
        "&size=5" +
        "&type_id=" + encodeURIComponent(type) +
        "&device_id=" + encodeURIComponent(device) +
        "&country_id=" + encodeURIComponent(country) +
        "&name=" + encodeURIComponent(name);
    if (sortAmount) {
        url += "&sort=amount," + sortAmount;
    }
    url += "&sort=id,asc";

    $.get({
        url: url,
        contentType: "application/json",
        dataType: "json",
        success: function (data, status, xhr) {
            $("#filterFormError").hide();
            const listContainer = $("#detailsList");
            listContainer.show();
            listContainer.empty(); // Очистить список перед добавлением новых данных

            data.content.forEach(detail => {
                listContainer.append(`
                    <li class="list-group-item">
                        ${detail.name}
                        <button class="btn btn-info btn-sm" onclick="showDetail(${detail.id})">Подробнее</button>
                        <button class="btn btn-warning btn-sm" onclick="showUpdateForm(${detail.id})">Обновить</button>
                        <button class="btn btn-danger btn-sm" onclick="deleteDetail(${detail.id})">Удалить</button>
                    </li>
                `);
            });
            currentPage = page;
            $("#pageNumber").text("Страница: " + (currentPage + 1));
            togglePaginationButtons(data.first, data.last);
        },
        error: function (xhr) {
            $("#filterFormError").text("Details for your request are not found").show();
            $("#detailsList").hide();
        }
    });
}

function deleteDetail(detailId) {
    $.ajax({
        url: "/details/" + detailId,
        type: "DELETE",
        success: function () {
            loadDetails(currentPage);
            closeModal('detailModal');
        }
    });
}

function togglePaginationButtons(first, last) {
    if (!first) {
        $("#prevPage").show();
    } else {
        $("#prevPage").hide();
    }
    if (!last) {
        $("#nextPage").show();
    } else {
        $("#nextPage").hide();
    }
}

function showUpdateForm(detailId) {

    openModal('editModal');

    $("#editFormError").hide().text(""); // Скрыть ошибку, если была

    $.get({
        url: "/details/" + detailId,
        success: function (data) {
            $("#editDetailName").val(data.name)
            $("#editDetailType").append(`<option value="${data.type.id}">${data.type.name}</option>`)
            $("#editDetailDevice").append(`<option value="${data.device.id}">${data.device.brand.name} ${data.device.model}</option>`)
            $("#editDetailCountry").append(`<option value="${data.country.id}">${data.country.name}</option>`)
            $("#editDetailAmount").val(data.amount)
            $("#editDetailType").focus(function () { showTypes("#editDetailType") });
            $("#editDetailDevice").focus(function () { showDevices("#editDetailDevice") });
            $("#editDetailCountry").focus(function () { showCountries("#editDetailCountry") });
        }
    });

    $("#editForm").off("submit"); // Очищаем старые обработчики

    $("#editForm").submit(function (e) {
        e.preventDefault();
        const updateData = {
            name: $("#editDetailName").val(),
            type: {
                id: $("#editDetailType").val()
            },
            device: {
                id: $("#editDetailDevice").val()
            },
            country: {
                id: $("#editDetailCountry").val()
            },
            amount: $("#editDetailAmount").val()
        };

        $.ajax({
            url: "/details/" + detailId,
            type: "PUT",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(updateData),
            success: function (response) {
                closeModal('editModal');
                showDetail(response.id);
                loadDetails(currentPage);
            },
            error: function (xhr) {
                errorHandle(xhr, "#editFormError", "detail");
            }
        });
    });
}


function errorHandle(xhr, selector, entityName) {
    if (!('message' in xhr.responseJSON)) {
        Object.keys(xhr.responseJSON).forEach(key => {
            $(selector).text(key + " is not valid").show();
        });
    } else {
        $(selector).text(xhr.responseJSON.message + ": This " + entityName + " is already exists").show();
    }
}

function showDetailCreateForm() {

    openModal('createModal');

    $("#createFormError").hide().text("");

    $("#createForm").off("submit");

    $("#createForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            name: $("#newDetailName").val(),
            type: {
                id: $("#newDetailType").val()
            },
            device: {
                id: $("#newDetailDevice").val()
            },
            country: {
                id: $("#newDetailCountry").val()
            },
            amount: $("#newDetailAmount").val()
        };
        $.post({
            url: "/details",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function (response) {
                closeModal('createModal');
                showDetail(response.id);
                loadDetails(currentPage);
            },
            error: function (xhr) {
                errorHandle(xhr, "#createFormError", "detail");
            }
        });
    });
}

function showDevices(selector) {
    let optionCount = $(selector + " option").length;
    if (optionCount === 1 || optionCount === 0) {
        $.get({
            url: "/devices",
            success: function (data) {
                data.content.forEach(function (device) {
                    $(selector).append(`<option value="${device.id}">${device.brand.name} ${device.model}</option>`);
                });
            }
        });
    }
}

function showTypes(selector) {
    let optionCount = $(selector + " option").length;
    if (optionCount === 1 || optionCount === 0) {
        $.get({
            url: "/types",
            success: function (data) {
                data.content.forEach(function (type) {
                    $(selector).append(`<option value="${type.id}">${type.name}</option>`);
                });
            }
        });
    }
}

function showCountries(selector) {
    let optionCount = $(selector + " option").length;
    if (optionCount === 1 || optionCount === 0) {
        $.get({
            url: "/countries",
            success: function (data) {
                data.content.forEach(function (country) {
                    $(selector).append(`<option value="${country.id}">${country.name}</option>`);
                });
            }
        });
    }
}

function showBrands(selector) {
    let optionCount = $(selector + " option").length;
    if (optionCount === 1 || optionCount === 0) {
        $.get({
            url: "/brands",
            success: function (data) {
                data.content.forEach(function (brand) {
                    $(selector).append(`<option value="${brand.id}">${brand.name}</option>`);
                });
            }
        });
    }
}

function showCategories(selector) {
    let optionCount = $(selector + " option").length;
    if (optionCount === 1 || optionCount === 0) {
        $.get({
            url: "/categories",
            success: function (data) {
                data.content.forEach(function (category) {
                    $(selector).append(`<option value="${category.id}">${category.name}</option>`);
                });
            }
        });
    }
}

function showDetail(detailId) {
    $.get({
        url: "/details/" + detailId,
        success: function (data) {
            $("#detailInfo").html(`
                <strong>Название:</strong> ${data.name} <br>
                <strong>Категория:</strong> ${data.type.name} <br>
                <strong>Количество:</strong> ${data.amount} <br>
                <strong>Для устройства:</strong> ${data.device.brand.name} ${data.device.model} <br>
                <strong>Страна производитель:</strong> ${data.country.name} <br>
                <button class="btn btn-warning btn-sm" onclick="showUpdateForm(${data.id})">Обновить</button>
                <button class="btn btn-danger btn-sm" onclick="deleteDetail(${data.id})">Удалить</button>
            `);
            openModal('detailModal');
        }
    });
}



function closeModal(modalId) {
    $("#" + modalId).modal('hide');
}

function openModal(modalId) {
    $("#" + modalId).modal('show');
}


function showTypeCreateForm() {

    openModal('createTypeModal')

    $("#createTypeFormError").hide().text("");

    $("#createTypeForm").off("submit");

    $("#createTypeForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            name: $("#createTypeName").val(),
        };
        $.post({
            url: "/types",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function () {
                closeModal('createTypeModal');
                $("#filterType").empty().append(`<option value="">Все</option>`)
                $("#newDetailType").empty()
                $("#editDetailType").empty()
                loadEntities("#entitySelector", entitiesPage)
            },
            error: function (xhr) {
                errorHandle(xhr, "#createTypeFormError", "type");
            }
        });
    });
}

function showCountryCreateForm() {
    openModal('createCountryModal')

    $("#createCountryFormError").hide().text("");

    $("#createCountryForm").off("submit");

    $("#createCountryForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            name: $("#createCountryName").val(),

        };
        $.post({
            url: "/countries",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function () {
                closeModal('createCountryModal');
                $("#filterCountry").empty().append(`<option value="">Все</option>`)
                $("#newDetailCountry").empty()
                $("#editDetailCountry").empty()
                loadEntities("#entitySelector", entitiesPage)
            },
            error: function (xhr) {
                errorHandle(xhr, "#createCountryFormError", "country");
            }
        });
    });
}

function showBrandCreateForm() {
    openModal('createBrandModal')

    $("#createBrandFormError").hide().text("");

    $("#createBrandForm").off("submit");

    $("#createBrandForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            name: $("#createBrandName").val(),

        };
        $.post({
            url: "/brands",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function () {
                closeModal('createBrandModal')
                $("#createDeviceBrand").empty()
                loadEntities("#entitySelector", entitiesPage)
            },
            error: function (xhr) {
                errorHandle(xhr, "#createBrandFormError", "brand");
            }
        });
    });
}

function showDeviceCreateForm() {
    openModal('createDeviceModal')

    $("#createDeviceFormError").hide().text("");

    $("#createDeviceForm").off("submit");

    $("#createDeviceForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            brand: {
                id: $("#createDeviceBrand").val()
            },
            model: $("#createDeviceModel").val(),
            category: {
                id: $("#createDeviceCategory").val()
            }
        };
        $.post({
            url: "/devices",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function () {
                closeModal('createDeviceModal');
                $("#filterDevice").empty().append(`<option value="">Все</option>`)
                $("#newDetailDevice").empty()
                $("#editDetailDevice").empty()
                loadEntities("#entitySelector", entitiesPage)
            },
            error: function (xhr) {
                errorHandle(xhr, "#createDeviceFormError", "device");
            }
        });
    });
}

function showCategoryCreateForm() {
    openModal('createCategoryModal')

    $("#createCategoryFormError").hide().text("");

    $("#createCategoryForm").off("submit");

    $("#createCategoryForm").submit(function (e) {
        e.preventDefault();
        const createData = {
            name: $("#createCategoryName").val(),

        };
        $.post({
            url: "/categories",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(createData),
            success: function () {
                closeModal('createCategoryModal');
                $("#createDeviceCategory").empty()
                loadEntities("#entitySelector", entitiesPage)
            },
            error: function (xhr) {
                errorHandle(xhr, "#createCategoryFormError", "category");
            }
        });
    });
}


$(document).ready(function () {
    if (window.location.pathname === "/") {
        loadDetails(currentPage);

        $("#prevPage").click(function () {
            if (currentPage > 0) {
                loadDetails(currentPage - 1);
            }
        });

        $("#nextPage").click(function () {
            if (currentPage >= 0) {
                loadDetails(currentPage + 1);
            }
        });

        $(".addTypeBtn").click(function () {
            showTypeCreateForm()
        })

        $(".addDeviceBtn").click(function () {
            showDeviceCreateForm()
        })

        $(".addCountryBtn").click(function () {
            showCountryCreateForm()
        })

        $(".addBrandBtn").click(function () {
            showBrandCreateForm()
        })

        $(".addCategoryBtn").click(function () {
            showCategoryCreateForm()
        })

        $("#createDeviceBrand").focus(function () {
            showBrands("#createDeviceBrand")
        })
        $("#createDeviceCategory").focus(function () {
            showCategories("#createDeviceCategory")
        })

        $("#filterType").focus(function () {
            showTypes("#filterType")
        });
        $("#filterDevice").focus(function () {
            showDevices("#filterDevice")
        });

        $("#filterCountry").focus(function () {
            showCountries("#filterCountry")
        });

        $("#newDetailType").focus(function () {
            showTypes("#newDetailType")
        });
        $("#newDetailDevice").focus(function () {
            showDevices("#newDetailDevice")
        });
        $("#newDetailCountry").focus(function () {
            showCountries("#newDetailCountry")
        });

        $("#addingButton").click(function () {
            showDetailCreateForm();
        });

        $("#filterForm select").change(function () {
            currentPage = 0;
            loadDetails(currentPage);
        });

        let nameInputTimeout;
        $("#filterName").on("input", function () {
            clearTimeout(nameInputTimeout);
            nameInputTimeout = setTimeout(() => {
                currentPage = 0;
                loadDetails(currentPage);
            }, 300); // задержка 300мс после последнего ввода
        });
    }
});
