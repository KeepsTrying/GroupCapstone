$(document).ready(function () {
    var lowerBound = 6;

    $('.row div').on('mouseenter', function () {
        $('div', this).addClass('transbox');
    });

    $('.row div').on('mouseleave', function () {
        $('div', this).removeClass('transbox');
    });

    $('.row div').on('mouseenter', function () {
        $('p', this).css('visibility', 'visible');
    });

    $('.row div').on('mouseleave', function () {
        $('p', this).css('visibility', 'hidden');
    });
    
    $('#showMoreBtn').click(function() {
        $.ajax({
            method: "GET",
            url: "http://localhost:8080/JackFontaneauxPearlRecovery/next6Posts/"+lowerBound,
            success: function (posts) {
                $('errorMessages').empty();
                $.each(posts, function(index, post){
                    var postId = post.postId;
                    var title = post.title;
                    var synopsis = post.synopsis;
                    var postDate = post.postDate;
                    var authorList = post.authorList;
                    console.log(authorList);
                    console.log(authorList[1]);
                    var imageList = post.imageList;
                    console.log(imageList);
                    console.log(imageList[i]);
                    var thumbImage;
                    
                    for(var i = 0; i < imageList.length; i++){
                        if(imageList[i].imageTypeId === 1){
                            var image = imageList[i];
                            thumbImage = image.imageUrl;
                        }
                    }
                    
                    
                    
                    var postToAdd = '<div class="col-sm-3 thum-buffer myFont"';
                    postToAdd += 'id="'+postId+'"';
                    postToAdd += 'style="background-image:url("'+thumbImage+'")";background-size:cover;color:black;>'
                    postToAdd += '<div class="container-fluid">';
                    for(var i = 0; i < authorList.length; i++){
                        var author = authorList[i];
                        var authorFirst = author.firstName;
                        var authorLast = author.lastName;
                        
                        postToAdd += '<p>'+authorFirst+' '+authorLast+',</p>';
                    }
                    postToAdd += '<br>';
                    postToAdd += '<p>'+title+'</p><br>';
                    postToAdd += '<p>'+synopsis+'</p><br>';
                    postToAdd += postDate.year + "-" + postDate.monthValue + "-" + postDate.dayOfMonth;
                    postToAdd += '</div>';
                    
                    addPost(postToAdd);
                })
            },
            error: function () {
            $('#errorMessages').append('<li>').attr({class: 'list-group-item list-group-item-danger'}).text('Error calling web service. Please try again later.');
            }
        })
        lowerBound += 6;
    });
});

function addPost(post){
    $('#thumDiv').append(post);
};