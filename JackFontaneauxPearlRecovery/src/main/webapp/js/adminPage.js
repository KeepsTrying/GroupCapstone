$(document).ready(function () {
    
    loadPublishedPages(1);
    loadUnpublishedPages(1);
    // loadDraftPages(1);
    
    



});


function loadPublishedPages(pageNum) {
	var publishedTable = $('#publishedTable');

	if (pageNum == 1) {
		$('#previousPublished').hide();
	} else {$('#previousPublished').show();};

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/JackFontaneauxPearlRecovery/adminPage/published/' + pageNum,
		success: function(model) {

			var numberOfPages = model.numberOfPages;

			if (numberOfPages == pageNum || numberOfPages == 0) {
				$('#nextPublished').hide();
			} else {$('#nextPublished').show();};

			var publishedStaticPagesArray = model.pages;

			$.each(publishedStaticPagesArray, function(index, publishedStaticPage) {
				var pageId = publishedStaticPage.pageId;
				var pageTitle = publishedStaticPage.title;
				var publishDate = publishedStaticPage.publishDate;

				var loc = $(location).attr('href');
				var pathName = loc.substring(0, loc.lastIndexOf('/'));

				var publishedWork  = '<tr>';
					publishedWork += '<td>';
					publishedWork += '<a href="' + pathName + '/category/' + pageId + '">';
					publishedWork += pageTitle;
					publishedWork += '</a>';
					publishedWork += '</td>';
					publishedWork += '<td>' + publishDate.year + '-' + publishDate.monthValue + '-' + publishDate.dayOfMonth + '</td>';
					publishedWork += '<td>';
					publishedWork += '<a href="' + pathName + '/edit/page/' + pageId + '">Edit</a>';
					publishedWork += '</td>';
					publishedWork += '<td>';
					publishedWork += '<a href="' + pathName + '/delete/page/' + pageId + '">Delete</a>';
					publishedWork += '</td>';
					publishedWork += '<td>';
					publishedWork += '<a href="' + pathName + '/unPublishPage/' + pageId + '">Unpublish</a>';
					publishedWork += '</td>';
					publishedWork += '</tr>';

					publishedTable.append(publishedWork);

			});

		},
		error:function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseJSON.message);
		}


	});


};



function loadUnpublishedPages(pageNum) {
	var unpublishedTable = $('#unpublishedTable');

	if (pageNum == 1) {
		$('#previousUnpublished').hide();
	} else {$('#previousUnpublished').show();};

	$.ajax({
		type: 'GET',
		url: 'http://localhost:8080/JackFontaneauxPearlRecovery/adminPage/unpublished/' + pageNum,
		success: function(model) {

			var numberOfPages = model.numberOfPages;

				if (numberOfPages == pageNum || numberOfPages == 0) {
					$('#nextUnpublished').hide();
				} else {$('#nextUnpublished').show();};

			var unpublishedStaticPagesArray = model.pages;

			$.each(unpublishedStaticPagesArray, function(index, unpublishedStaticPage) {
				var pageId = unpublishedStaticPage.pageId;
				var pageTitle = unpublishedStaticPage.title;
				var publishDate = unpublishedStaticPage.publishDate;

				var loc = $(location).attr('href');
				var pathName = loc.substring(0, loc.lastIndexOf('/'));

				var unpublishedWork  = '<tr>';
					unpublishedWork += '<td>';
					unpublishedWork += '<a href="' + pathName + '/category/' + pageId + '">';
					unpublishedWork += pageTitle;
					unpublishedWork += '</a>';
					unpublishedWork += '</td>';
					unpublishedWork += '<td>' + publishDate.year + '-' + publishDate.monthValue + '-' + publishDate.dayOfMonth + '</td>';
					unpublishedWork += '<td>';
					unpublishedWork += '<a href="' + pathName + '/edit/page/' + pageId + '">Edit</a>';
					unpublishedWork += '</td>';
					unpublishedWork += '<td>';
					unpublishedWork += '<a href="' + pathName + '/delete/page/' + pageId + '">Delete</a>';
					unpublishedWork += '</td>';
					unpublishedWork += '<td>';
					unpublishedWork += '<a href="' + pathName + '/publishPage/' + pageId + '">Publish</a>';
					unpublishedWork += '</td>';
					unpublishedWork += '</tr>';

					unpublishedTable.append(unpublishedWork);

			});

		},
		error:function(jqXHR, textStatus, errorThrown) {
			alert(jqXHR.responseJSON.message);
		}


	});


};


function nextPublishedPages() {
    $('#publishedTable tr').remove();
    var previousValue = parseInt($('#previousPublished').val(), 10) + 1;
    var nextValue = parseInt($('#nextPublished').val(), 10) + 1;

    loadPublishedPages(nextValue);

    $('#previousPublished').val(previousValue);
    $('#nextPublished').val(nextValue);
};


function previousPublishedPages() {
    $('#publishedTable tr').remove();

    var previousValue = parseInt($('#previousPublished').val(), 10);

    loadPublishedPages(previousValue);

    var previousValue = prevousValue - 1;
    var nextValue = parseInt($('#nextPublished').val(), 10) - 1;

    $('#previousPublished').val(previousValue);
    $('#nextPublished').val(nextValue);
};


function nextUnpublishedPages() {
    $('#unpublishedTable tr').remove();
    var previousValue = parseInt($('#previousUnpublished').val(), 10) + 1;
    var nextValue = parseInt($('#nextUnpublished').val(), 10) + 1;

    loadUnpublishedPages(nextValue);

    $('#previousUnpublished').val(previousValue);
    $('#nextUnpublished').val(nextValue);
};


function previousUnpublishedPages() {
    $('#unpublishedTable tr').remove();

    var previousValue = parseInt($('#previousUnpublished').val(), 10);

    loadUnpublishedPages(previousValue);

    var previousValue = prevousValue - 1;
    var nextValue = parseInt($('#nextUnpublished').val(), 10) - 1;

    $('#previousUnpublished').val(previousValue);
    $('#nextUnpublished').val(nextValue);
};