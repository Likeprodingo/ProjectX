function open_modal(id,name,surname,mark,answer,task) {
    $("#exampleModal").modal('show');
    document.getElementById("text").value = task;
    document.getElementById("answer").textContent = answer;
    document.getElementById("id-task").value = id;
    document.getElementById("model-title").innerText = "Task " + name + " " + surname;
    document.getElementById("mark").value = mark;
    $('#myInput').trigger('focus')
}
