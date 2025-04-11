let currentPage = 0;
let totalPages = 0;

function loadDetails(page) {
    $.ajax({
        url: "/details?page=" + page + "&size=5",
        method: "GET",
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

            // Получаем количество страниц из заголовков
            const totalPagesHeader = xhr.getResponseHeader("X-Total-Pages");
            totalPages = totalPagesHeader ? parseInt(totalPagesHeader) : 0;

            // Показываем/скрываем кнопки
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

    if (currentPage >= totalPages - 1) {
        $("#nextPage").hide();
    } else {
        $("#nextPage").show();
    }
}

function showDetail(detailId) {
    $.ajax({
        url: "/details/" + detailId,
        method: "GET",
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

$("#closeModal").click(function () {
    $("#detailModal").hide();
});

$(document).ready(function () {
    loadDetails(currentPage);

    $("#prevPage").click(function () {
        if (currentPage > 0) {
            loadDetails(currentPage - 1);
        }
    });

    $("#nextPage").click(function () {
        if (currentPage < totalPages - 1) {
            loadDetails(currentPage + 1);
        }
    });
});
