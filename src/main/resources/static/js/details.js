let currentPage = 0;

function loadDetails(page) {
    $.get({
        url: "/details?page=" + page + "&size=5",
        success: function (data, status, xhr) {
            const listContainer = $("#detailsList");
            listContainer.empty(); // Очистить список перед добавлением новых данных

            data.forEach(detail => {
                listContainer.append(`
                    <li>
                        ${detail.name} 
                        <button onclick="showDetail(${detail.id})">Подробнее</button>
                    </li>
                `);
            });
            currentPage = page;
            $("#pageNumber").text("Страница: " + (currentPage + 1));

            togglePaginationButtons();
        },
        error: function () {
            alert("Ошибка загрузки данных!");
        }
    });
}

function togglePaginationButtons() {
    if (currentPage === 0) {
        $("#prevPage").hide();
    } else {
        $("#prevPage").show();
    }
}

function showDetail(detailId) {
    $.get({
        url: "/details/" + detailId,
        success: function (data) {
            $("#detailInfo").text(`
                Название: ${data.name}
                Количество: ${data.amount}
                Для устройства: ${data.device.brand.name} ${data.device.model}
            `);
            $("#detailModal").show();
        },
        error: function () {
            alert("Ошибка загрузки подробной информации");
        }
    });
}

$(document).on("click", "#closeModal", function () {
    $("#detailModal").fadeOut();
});

$(document).ready(function () {
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
});
