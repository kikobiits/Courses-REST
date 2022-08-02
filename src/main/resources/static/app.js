let loadCoursesButton = document.getElementById('loadCourses')

loadCoursesButton.addEventListener('click', onLoadCourses);

function onLoadCourses(event) {

    event.preventDefault();

    // postman code snippet for get request

    var requestOptions = {
        method: 'GET',
        redirect: 'follow'
    };

    let courseContainer = document.getElementById('courses-container');
    courseContainer.innerHTML = '';

    fetch("http://localhost:8080/api/courses", requestOptions)
        .then(response => response.json())
        .then(json => json.forEach(course => {

            //adding rows to html table

            let row = document.createElement('tr');

            let idCol = document.createElement('td');
            let titleCol = document.createElement('td');
            let lecturerCol = document.createElement('td');
            let roomCol = document.createElement('td');

            idCol.textContent = course.id;
            titleCol.textContent = course.title;
            lecturerCol.textContent = course.lecturer;
            roomCol.textContent = course.room;

            row.appendChild(idCol);
            row.appendChild(titleCol);
            row.appendChild(roomCol);
            row.appendChild(lecturerCol);

            courseContainer.appendChild(row);

        }))
        .catch(error => console.log('error', error));
}

// function to target the form's contents

document.querySelector('#my-form').addEventListener('submit', function () {
    event.preventDefault();

    var title = event.target.elements.title.value;
    var room = event.target.elements.room.value;
    var lecturer = event.target.elements.lecturer.value;

    // class for making a request

    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8080/api/courses/submit", true);

    // declare request headers for content type json

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    // creating json object

    var data = {
        "title": title,
        "room": room,
        "lecturer": lecturer
    };

    xhr.send(JSON.stringify(data));
})

// !----! DELETE Logic

let deleteButton = document.getElementById('deleteButton')

deleteButton.addEventListener('click', onDeleteCourse);

function onDeleteCourse(event) {

    event.preventDefault();

    let courseDeleteId = document.getElementById('courseDeleteId').value;

    var requestOptions = {
        method: 'DELETE',
        redirect: 'follow'
    };

    fetch(`http://localhost:8080/api/courses/${courseDeleteId}`, requestOptions)
        .then(response => response.json())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
}

// !----! UPDATE Logic

document.querySelector('#update-form').addEventListener('submit', function () {
    event.preventDefault();

    let courseUpdateId = document.getElementById('courseUpdateId').value;

    var id = document.getElementById('courseUpdateId').value;
    var title = document.getElementById('titleUpdate').value;
    var room = document.getElementById('roomUpdate').value;
    var lecturer = document.getElementById('lecturerUpdate').value;

    // class for making a request

    let xhr = new XMLHttpRequest();
    xhr.open("PUT", `http://localhost:8080/api/courses/${courseUpdateId}`, true);

    // declare request headers for content type json

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");

    // creating json object

    var data = {
        "id": id,
        "title": title,
        "room": room,
        "lecturer": lecturer
    };

    xhr.send(JSON.stringify(data));
})