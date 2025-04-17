let entitiesPage = 0

$(document).ready(function () {
    loadEntities("#entitySelector", entitiesPage)

    $("#entitySelector").change(function () {
        entitiesPage = 0
        loadEntities("#entitySelector", entitiesPage)
    })

    $("#addingEntityButton").click(function () {
        createEntity($("#entitySelector").val())
    })

    $("#createDeviceBrand").focus(function () {
        showBrands("#createDeviceBrand")
    })

    $("#createDeviceCategory").focus(function () {
        showCategories("#createDeviceCategory")
    })

    $(".addBrandBtn").click(function () {
        showBrandCreateForm()
    })

    $(".addCategoryBtn").click(function () {
        showCategoryCreateForm()
    })
})

function loadEntities(selector, page) {
    console.log("Calling loadEntity with", selector, page)
    let entity = $(selector).val()
    entity = entity === "devices"
    || entity === "categories"
    || entity === "types"
    || entity === "countries"
    || entity === "brands" ? entity : ""

    const url = "/" + entity + "?page=" + page

    if (entity && entity !== "devices") {
        $.get({
            url: url,
            contentType: "application/json",
            dataType: "json",
            success: function (data, status, xhr) {
                $("#entitiesNotFoundError").hide()
                const listContainer = $("#entitiesList")
                listContainer.show()
                listContainer.empty()

                data.content.forEach(foundEntity => {
                    listContainer.append(`
                        <li class="list-group-item d-flex align-items-center justify-content-between">
                            <span>${foundEntity.name}</span>
                            <div class="d-flex align-items-center">
                                <button class="btn btn-danger btn-sm mr-2" onclick="deleteEntity(${foundEntity.id}, '${entity}', this)">Удалить</button>
                                <div class="text-danger small deleteEntityError" style="display: none;"></div>
                            </div>
                        </li>
                    `)
                })

                currentPage = page
                $("#pageNumber").text("Страница: " + (currentPage + 1))
                togglePaginationButtons(data.first, data.last)
            },
            error: function (xhr) {
                $("#entitiesNotFoundError").text("Nothing was found").show()
                $("#entitiesList").hide()
            }
        })
    } else {
        $.get({
            url: url,
            contentType: "application/json",
            dataType: "json",
            success: function (data, status, xhr) {
                $("#entitiesNotFoundError").hide()
                const listContainer = $("#entitiesList")
                listContainer.show()
                listContainer.empty()

                data.content.forEach(foundEntity => {
                    listContainer.append(`
                        <li class="list-group-item d-flex align-items-center justify-content-between">
                            <span>${foundEntity.brand.name} ${foundEntity.model} ${foundEntity.category.name}</span>
                            <div class="d-flex align-items-center">
                                <button class="btn btn-danger btn-sm mr-2" onclick="deleteEntity(${foundEntity.id}, '${entity}', this)">Удалить</button>
                                <div class="text-danger small deleteEntityError" style="display: none;"></div>
                            </div>
                        </li>
                    `)
                })

                currentPage = page
                $("#pageNumber").text("Страница: " + (currentPage + 1))
                togglePaginationButtons(data.first, data.last)
            },
            error: function (xhr) {
                $("#entitiesNotFoundError").text("Nothing was found").show()
                $("#entitiesList").hide()
            }
        })
    }
}

function deleteEntity(entityId, entity, deleteButton) {
    $.ajax({
        url: "/" + entity + "/" + entityId,
        type: "DELETE",
        success: function () {
            loadEntities("#entitySelector", entitiesPage)
        },
        error: function () {
            $(deleteButton).siblings(".deleteEntityError")
                .text("Unable to delete")
                .fadeIn(200)
                .delay(1000)
                .fadeOut(200)
        }
    })
}

function createEntity(entityName) {
    if (entityName === "types") {
        showTypeCreateForm()
    } else if (entityName === "countries") {
        showCountryCreateForm()
    } else if (entityName === "categories") {
        showCategoryCreateForm()
    } else if (entityName === "brands") {
        showBrandCreateForm()
    } else if (entityName === "devices") {
        showDeviceCreateForm()
    }
}
