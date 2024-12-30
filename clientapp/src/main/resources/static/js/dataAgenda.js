console.log("Testing");

// menampilkan tanggal
document.addEventListener("DOMContentLoaded", function () {
    const dateElement = document.getElementById("current-date");
    const today = new Date();

    // Format tanggal (contoh: 18 Desember 2024)
    const options = { day: "numeric", month: "long", year: "numeric" };
    const formattedDate = today.toLocaleDateString("id-ID", options);

    // Update teks di dalam elemen <h1>
    dateElement.textContent = `Tanggal: ${formattedDate}`;
});

// tampilkan data di table
$(document).ready(() => {
    $("#tb-agenda").DataTable({
        ajax: {
            method: "GET",
            url: "/api/agendas",
            dataSrc: "",
        },
        columnDefs: [{ className: "text-center", targets: "_all" }],
        columns: [
            { data: "id_agenda" }, // Assuming the ID field is named 'id_agenda'
            { data: "judul_agenda" }, // Assuming the title field is named 'judul_agenda'
            { data: "agenda" }, // Assuming the agenda description field is named 'agenda'
            { data: "tahun_ajaran" }, // Assuming the academic year field is named 'tahun_ajaran'
            {
                data: null,
                render: (data) => {
                    return /* html */ `
                        <div class="d-flex gap-3 justify-content-center align-items-center">
                            <button
                                type="button"
                                class="btn btn-warning btn-sm"
                                data-bs-toggle="modal"
                                data-bs-target="#update"
                                onclick="beforeUpdate(${data.id_agenda})"
                            >
                                Update
                            </button>
                            <button
                                type="button"
                                class="btn btn-danger btn-sm"
                                onclick="deleteAgenda(${data.id_agenda})"
                            >
                                Delete
                            </button>
                        </div>
                    `;
                },
            },
        ],
    });
});

// Create Agenda
