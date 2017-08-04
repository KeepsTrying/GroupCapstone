/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {

    var role = $('#role').val();

    if (role !== 'Admin') {
        loadCommentsNormal($('#post-id').val());
    }
    if (role === 'Admin') {
        loadCommentsAdmin($('#post-id').val());
    }
    
    var currentCitySpan = $('#json-city').empty();
	var currentIconDiv = $('#current-icon').empty();
	var currentDataDiv = $('#current-data').empty();

	var lat = $('#post-lat').val();
	
	var long = $('#post-long').val();
	

	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/weather?lat=' + lat + '&lon=' + long + '&units=imperial&appid=54a9d2703cd5cc7468b6d34353f75114',
		success: function(dataObject){
			$('#current').show();
			var city = dataObject.name;
			currentCitySpan.append().text(city);

			$.each(dataObject.weather, function(index, data){

				//var city = data.name;
				var currentIcon = data.icon;
				var currentIconURL = 'http://openweathermap.org/img/w/' + currentIcon + '.png';
				var currentConditions = data.description;
				var currentMainCondition = data.main;
				//var currentTemp = data.main.temp;
				//var currentHumidity = data.main.humidity;
				//var currentWindSpeed = data.wind.speed;

				var rowIconDiv = '<tr>';
					rowIconDiv += '<td><img src = "' + currentIconURL + '"></td>';
					rowIconDiv += '<td>' + currentMainCondition + ": " + currentConditions + '</td>';

				//currentCitySpan.append().text(city);
				currentIconDiv.append(rowIconDiv);
				//currentDataDiv.append(dataP1);


			});

			var currentTemp = dataObject.main.temp;
			var currentHumidity = dataObject.main.humidity;
			var currentWindSpeed = dataObject.wind.speed;

			var dataP1 = '<p>' + "Temperature: " + currentTemp + ' F</p>';
			var dataP2 = '<p>' + "Humidity: " + currentHumidity + "%" + '</p>';
			var dataP3 = '<p>' + "Wind: " + currentWindSpeed + " miles/hour" + '</p>';

			currentDataDiv.append(dataP1 + dataP2 + dataP3);



		},
		error: function() {
			$('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');

		}

	});

	$.ajax({
		type: 'GET',
		url: 'http://api.openweathermap.org/data/2.5/forecast/daily?lat=' + lat + '&lon=' + long + '&units=imperial&appid=54a9d2703cd5cc7468b6d34353f75114',
		success: function(dataArrayObject){
			$('#five-day').show();
			$('#five-day-cast').empty();
			var index = 0;
			$.each(dataArrayObject.list, function(index, day){
				if(index < 5){
					var rawDate = new Date(day.dt * 1000);
					console.log(rawDate);
					var day = rawDate.getDate();
					var month = rawDate.toString().substring(4,7);
					var tempMax = this.temp.max;
					var tempMin = this.temp.min;
					var icon = this.weather[0].icon;
					var currentIconUrl = 'http://openweathermap.org/img/w/' + icon + '.png'; 
					var currentCondition = this.weather[0].main;

					
					var dataP1 = '<p>' + day + month + '</p>';
					var dataP2 = '<p><img src = "' + currentIconUrl + '">' + currentCondition + '</p>';
					var dataP3 = '<p>' + "H " + tempMax + "F L " + tempMin + "F" + '</p>';
					

			
					var forecastDiv = '<div class="col-sm-2">' + dataP1 + dataP2 + dataP3 + '</div>';

					$('#five-day-cast').append(forecastDiv);
					index++;
			    }

			});


		},

		error: function(){
			$('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
		}

	});

    $('#add-comment').on('click', function (event) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/JackFontaneauxPearlRecovery/post/comment/create',
            data: JSON.stringify({
                name: $('#input-name').val(),
                postId: $('#post-id').val(),
                textContent: $('#input-text').val()
            }),
            headers: {
//                'Accept': 'application/json', not needed because nothing being returned
                'Content-Type': 'application/json'
            },
//            'dataType': 'json', not needed nothing being returned
            success: function () {
                $('#errorMessagesCreate').empty();
                $('#input-name').val('');
                $('#input-text').val('');

                if (role !== 'Admin') {
                    loadCommentsNormal($('#post-id').val());
                }
                if (role === 'Admin') {
                    loadCommentsAdmin($('#post-id').val());
                }

            },
            error: function () {
                $('#errorMessagesCreateComment').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
            }
        });
    });

});










function loadCommentsNormal(postId) {
    clearComments();
    var content = $('#comment-div');


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/JackFontaneauxPearlRecovery/post/comments/' + postId,
        success: function (comments) {
            console.log(comments);
            $.each(comments, function (index, comment) {
                var name = comment.name;
                var commentText = comment.textContent;
                var commentId = comment.commentId;
                console.log(commentId);

                var contentAdd = '<div class="row">';
                contentAdd += '<h3 class="pull-left">' + name + '</h3>';
                contentAdd += '<textarea style="color: black;" class="col-sm-12" disabled>' + commentText + '</textarea>';
                contentAdd += '</div>';
                content.append(contentAdd);
            });
        },
        error: function () {
            $('#errorMessagesDisplayComment').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');

        }
    });
}

function loadCommentsAdmin(postId) {
    clearComments();
    var content = $('#comment-div');


    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/JackFontaneauxPearlRecovery/post/comments/' + postId,
        success: function (comments) {
            console.log(comments);
            $.each(comments, function (index, comment) {
                var name = comment.name;
                var commentText = comment.textContent;
                var commentId = comment.commentId;
                console.log(commentId);

                var contentAdd = '<div class="row">';
                contentAdd += '<h3 class="pull-left">' + name + '</h3>';
                contentAdd += '<textarea class="col-sm-12" disabled>' + commentText + '</textarea>';
                contentAdd += '<sec:authorize access="hasRole(\'ROLE_ADMIN\')">';
                contentAdd += '<input type="hidden" value="' + commentId + '" id="comment-id" />';
                contentAdd += '<button onclick="deleteComment(' + commentId + ',' + postId + ')" id="remove-comment" type="button" class="btn btn-default pull-right">Remove Comment</button>';
                contentAdd += '</sec:authorize>';
                contentAdd += '</div>';
                content.append(contentAdd);
            });
        },
        error: function () {
            $('#errorMessagesDisplayComment').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
        }
    });
}


function clearComments() {
    $('#comment-div').html('');
}

function deleteComment(commentId, postId) {
    console.log(postId);
    console.log(commentId);
    var role = $('#role').html();
    if (confirm("Are you sure that you want to delete this entry?")) {

        $.ajax({
            type: 'DELETE',
            url: 'http://localhost:8080/JackFontaneauxPearlRecovery/post/comment/delete/' + commentId,
            success: function () {
                if (role !== 'Admin') {
                    loadCommentsNormal($('#post-id').val());
                }
                if (role === 'Admin') {
                    loadCommentsAdmin($('#post-id').val());
                }
            },
            error: function () {
                $('#errorMessagesDeleteComment').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
            }
        });
    } else {
        return false;
    }
}



