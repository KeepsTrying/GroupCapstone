 $(document).ready(function () {
    
    loadPublishedPosts(1);
    loadUnpublishedPosts(1);
    // loadDraftPages(1);
    
    //call intermediate function when button is clicked instead of the actual loadPublishedPost()
    //define intermediate function outside of document.ready with incrementer for button values
    //and call loadPublishedPosts() within intermediate function



});                           


function loadPublishedPosts(pageNum) {
    var publishedTable = $('#publishedTable');

    if (pageNum == 1) {
        $('#previousPublished').hide();
    } else {$('#previousPublished').show();};

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/JackFontaneauxPearlRecovery/adminPost2/published/' + pageNum,
        success: function(model) {

            var numberOfPages = model.numberOfPages;

            if (numberOfPages == pageNum || numberOfPages == 0) {
                $('#nextPublished').hide();
            } else {$('#nextPublished').show();};

            var publishedBlogPostsArray = model.posts;

            $.each(publishedBlogPostsArray, function(index, publishedBlogPost) {

                var postId = publishedBlogPost.postId;
                var postTitle = publishedBlogPost.title;
                var postDate = publishedBlogPost.postDate;

                var loc = $(location).attr('href');
                var pathName = loc.substring(0, loc.lastIndexOf('/'));

                var publishedWork  = '<tr>';
                    publishedWork += '<td>';
                    publishedWork += '<a href="' + pathName + '/post/' + postId + '">';
                    publishedWork += postTitle;
                    publishedWork += '</a>';
                    publishedWork += '</td>';
                    publishedWork += '<td>' + postDate.year + '-' + postDate.monthValue + '-' + postDate.dayOfMonth + '</td>';
                    publishedWork += '<td>';
                    publishedWork += '<a href="' + pathName + '/edit/post/' + postId + '">Edit</a>';
                    publishedWork += '</td>';
                    publishedWork += '<td>';
                    publishedWork += '<a href="' + pathName + '/delete/post/' + postId + '">Delete</a>';
                    publishedWork += '</td>';
                    publishedWork += '<td>';
                    publishedWork += '<a href="' + pathName + '/unPublishPost/' + postId + '">Unpublish</a>';
                    publishedWork += '</td>';
                    publishedWork += '</tr>';

                    publishedTable.append(publishedWork);


            })





        },
        error:function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseJSON.message);
        }

    });

};



function loadUnpublishedPosts(pageNum) {
    var unpublishedTable = $('#unpublishedTable');


    if (pageNum == 1) {
        $('#previousUnpublished').hide();
    } else {$('#previousUnpublished').show();};

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/JackFontaneauxPearlRecovery/adminPost2/unpublished/' + pageNum,
        success: function(model) {

            var numberOfPages = model.numberOfPages;

            if (numberOfPages == pageNum || numberOfPages == 0) {
                $('#nextUnpublished').hide();
            } else {$('#nextUnpublished').show();};

            var unpublishedBlogPostsArray = model.posts;

            $.each(unpublishedBlogPostsArray, function(index, unpublishedBlogPost) {

                var postId = unpublishedBlogPost.postId;
                var postTitle = unpublishedBlogPost.title;
                var postDate = unpublishedBlogPost.postDate;

                var loc = $(location).attr('href');
                var pathName = loc.substring(0, loc.lastIndexOf('/'));

                var unpublishedWork  = '<tr>';
                    unpublishedWork += '<td>';
                    unpublishedWork += '<a href="' + pathName + '/post/' + postId + '">';
                    unpublishedWork += postTitle;
                    unpublishedWork += '</a>';
                    unpublishedWork += '</td>';
                    unpublishedWork += '<td>' + postDate.year + '-' + postDate.monthValue + '-' + postDate.dayOfMonth + '</td>';
                    unpublishedWork += '<td>';
                    unpublishedWork += '<a href="' + pathName + '/edit/post/' + postId + '">Edit</a>';
                    unpublishedWork += '</td>';
                    unpublishedWork += '<td>';
                    unpublishedWork += '<a href="' + pathName + '/delete/post/' + postId + '">Delete</a>';
                    unpublishedWork += '</td>';
                    unpublishedWork += '<td>';
                    unpublishedWork += '<a href="' + pathName + '/publishPost/' + postId + '">Publish</a>';
                    unpublishedWork += '</td>';
                    unpublishedWork += '</tr>';

                    unpublishedTable.append(unpublishedWork);


            })





        },
        error:function(jqXHR, textStatus, errorThrown) {
            alert(jqXHR.responseJSON.message);
        }

    });

};


function nextPublishedPosts() {
    $('#publishedTable tr').remove();
    var previousValue = parseInt($('#previousPublished').val(), 10) + 1;
    var nextValue = parseInt($('#nextPublished').val(), 10) + 1;

    loadPublishedPosts(nextValue);

    $('#previousPublished').val(previousValue);
    $('#nextPublished').val(nextValue);
};


function previousPublishedPosts() {
    $('#publishedTable tr').remove();

    var previousValue = parseInt($('#previousPublished').val(), 10);
    
    loadPublishedPosts(previousValue);

    var previousValue = previousValue - 1;
    var nextValue = parseInt($('#nextPublished').val(), 10) - 1;
    var nextValue = $('#nextPublished').val() - 1;

    $('#previousPublished').val(previousValue);
    $('#nextPublished').val(nextValue);
};


function nextUnpublishedPosts() {
    $('#unpublishedTable tr').remove();
    var previousValue = parseInt($('#previousUnpublished').val(), 10) + 1;
    var nextValue = parseInt($('#nextUnpublished').val(), 10) + 1;

    loadUnpublishedPosts(nextValue);

    $('#previousUnpublished').val(previousValue);
    $('#nextUnpublished').val(nextValue);
};


function previousUnpublishedPosts() {
    $('#unpublishedTable tr').remove();

    var previousValue = parseInt($('#previousUnpublished').val(), 10);

    loadUnpublishedPosts(previousValue);

    var previousValue = prevousValue - 1;
    var nextValue = parseInt($('#nextUnpublished').val(), 10) - 1;

    $('#previousUnpublished').val(previousValue);
    $('#nextUnpublished').val(nextValue);
};