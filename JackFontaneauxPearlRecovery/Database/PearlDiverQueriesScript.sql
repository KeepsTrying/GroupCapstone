USE PearlDiver_Test;


-- --------------------------------------
-- Queries for Image DAO
-- --------------------------------------

-- SQL_ADD_IMAGE
INSERT INTO Images (Path) VALUES (?);

-- SQL_REMOVE_IMAGE
DELETE FROM PostsImages WHERE ImageId = ?; 
DELETE FROM PagesImages WHERE ImageId = ?; 
DELETE FROM Images WHERE ImageId = ?;

-- SQL_UPDATE_IMAGE
UPDATE Images 
SET Path = ? 
WHERE ImageId = ?;

-- SQL_UPDATE_PAGE_IMAGE
UPDATE PagesImages SET 
ImageId = ?
WHERE PageId = ?;

-- SQL_UPDATE_POST_IMAGE
UPDATE PostsImages SET 
ImageId = ? 
WHERE PostId = ?;

-- SQL_GET_IMAGE_BY_ID
SELECT Path FROM Images WHERE ImageId = ?;

-- SQL_GET_IMAGES_FOR_POST
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN Posts USING (PostId)
WHERE PostId = ?;

-- SQL_GET_IMAGES_FOR_PAGE
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PagesImages USING (ImageId)
LEFT JOIN StaticPages USING (PageId)
WHERE PageId = ?;

-- SQL_GET_PREVIEW_IMAGE_FOR_POST
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN Posts USING (PostId)
WHERE PostId = ? AND ImageTypeId = 1;

-- SQL_GET_HEADER_IMAGE_FOR_POST
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN Posts USING (PostId)
WHERE PostId = ? AND ImageTypeId = 2;

-- SQL_GET_BODY_IMAGE_FOR_POST
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN Posts USING (PostId)
WHERE PostId = ? AND ImageTypeId = 4;

-- SQL_GET_PREVIEW_IMAGE_FOR_PAGE
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN StaticPages USING (PageId)
WHERE PageId = ? AND ImageTypeId = 1;

-- SQL_GET_HEADER_IMAGE_FOR_PAGE
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PostsImages USING (ImageId)
LEFT JOIN StaticPages USING (PageId)
WHERE PageId = ? AND ImageTypeId = 2;

-- SQL_GET_BODY_IMAGE_FOR_PAGE
SELECT ImageId, Path, ImageType
FROM ImageTypes
INNER JOIN Images USING (ImageTypeId)
LEFT JOIN PagesImages USING (ImageId)
LEFT JOIN StaticPages USING (PageId)
WHERE PageId = ? AND ImageTypeId = 3;

-- SQL_GET_ALL_PREVIEW_IMAGES
USE PearlDiver_Test;
SELECT ImageId, Path, ImageType
FROM Images
INNER JOIN ImageTypes USING (ImageTypeId)
WHERE ImageTypeId = 1;

-- SQL_GET_ALL_HEADER_IMAGES
USE PearlDiver_Test;
SELECT ImageId, Path, ImageType
FROM Images
INNER JOIN ImageTypes USING (ImageTypeId)
WHERE ImageTypeId = 2;

-- SQL_GET_ALL_STATIC_PAGE_IMAGES
USE PearlDiver_Test;
SELECT ImageId, Path, ImageType
FROM Images
INNER JOIN ImageTypes USING (ImageTypeId)
WHERE ImageTypeId = 3;

-- SQL_GET_ALL_BLOG_POST_IMAGES
USE PearlDiver_Test;
SELECT ImageId, Path, ImageType
FROM Images
INNER JOIN ImageTypes USING (ImageTypeId)
WHERE ImageTypeId = 4;







-- --------------------------------------
-- Queries for Tag DAO
-- --------------------------------------

-- SQL_ADD_TAG
INSERT INTO Tags (Tag) VALUES (?);

-- SQL_REMOVE_TAG
DELETE FROM PostsTags WHERE TagId = ?; 
DELETE FROM Tags WHERE TagId = ?;

-- SQL_UPDATE_TAG
UPDATE Tags Set Tag = ? WHERE TagId = ?;

-- SQL_GET_TAG_BY_ID
SELECT Tag FROM Tags WHERE TagId = ?;

-- SQL_GET_TAG_BY_NAME
SELECT * FROM Tags
WHERE Tag LIKE '%?%';

-- SQL_GET_ALL_TAGS
SELECT Tag FROM Tags ORDER BY TagId ASC;


-- --------------------------------------
-- Queries for User DAO
-- --------------------------------------

-- SQL_ADD_USER
INSERT INTO Users (UserName, Password, Role, FirstName, LastName, Email) 
VALUES (?, ?, ?, ?, ?, ?);


-- remove associations: PostsUsers, PagesUsers
-- SQL_REMOVE_USER
DELETE FROM PostsUsers WHERE UserId = ?; 
DELETE FROM PagesUsers WHERE UserId = ?; 
DELETE FROM Users WHERE UserId = ?;

-- SQL_UPDATE_USER
UPDATE Users SET 
UserName = ?, 
Password = ?, 
Role = ?, 
FirstName = ?, 
LastName = ?, 
Email = ? 
WHERE UserId = ?;

-- SQL_GET_USER_BY_ID
SELECT * FROM Users WHERE UserId = ?;



-- --------------------------------------
-- Queries for StaticPage DAO
-- --------------------------------------

-- SQL_ADD_STATIC_PAGE
INSERT INTO StaticPages (PageCategoryId, NavName, Title, PublishDate, TextArea, Published) 
VALUES (?, ?, ?, ?, ?, ?);


-- remove associations: PagesImages, PagesUsers
-- SQL_REMOVE_STATIC_PAGE
DELETE FROM PagesUsers WHERE PageId = ?; 
DELETE FROM PagesImages WHERE PageId = ?; 
DELETE FROM StaticPages WHERE PageId = ?;

-- SQL_UPDATE_STATIC_PAGE
UPDATE StaticPages SET 
PageCategoryId = ?, 
NavName = ?, 
Title = ?, 
PublishDate = ?, 
TextArea = ?, 
Published = ? 
WHERE PageId = ?;

-- SQL_GET_STATIC_PAGE_BY_ID
SELECT * FROM StaticPages WHERE PageId = ?;

-- SQL_GET_IMAGES_FOR_STATIC_PAGE
SELECT Path FROM Images 
LEFT JOIN PagesImages USING (PageId) 
LEFT JOIN StaticPages USING (ImageId) 
WHERE ImageId = ?;

-- SQL_GET_AUTHORS_OF_STATIC_PAGE
SELECT UserId, UserName, Password, Role, FirstName, LastName, Email 
FROM Users 
LEFT JOIN PagesUsers USING (UserId) 
LEFT JOIN StaticPages USING (PageId) 
WHERE PageId = ?;

-- SQL_ADD_STATIC_IMAGE_BRIDGE
INSERT INTO PagesImages (PageId, ImageId) 
VALUES (?, ?);

-- SQL_ADD_STATIC_USER_BRIDGE
INSERT INTO PagesUsers (PageId, UserId) 
VALUES (?, ?);

-- SQL_GET_UNPUBLISHED_PAGES
SELECT * FROM StaticPages
WHERE Published = 0;

-- SQL_GET_PUBLISHED_PAGES
SELECT * FROM StaticPages
WHERE Published = 1;

-- SQL_GET_COMPLETED_PAGES
SELECT * FROM StaticPages
WHERE Completed = 1;

-- SQL_GET_INCOMPLETE_PLAGES
SELECT * FROM StaticPages
WHERE Completed = 0;

-- SQL_GET_CATEGORIES_FOR_PAGE
SELECT PageCategory 
FROM PageCategories 
LEFT JOIN StaticPages USING (PageCategoryId) 
WHERE PageId = ?;

-- SQL_GET_UNPUBLISHED_PAGE_OF_AUTHOR
SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed 
FROM StaticPages 
LEFT JOIN PagesUsers USING (PageId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Published = 0 ORDER BY PublishDate DESC;

-- SQL_GET_PUBLISHED_PAGE_OF_AUTHOR
SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed 
FROM StaticPages 
LEFT JOIN PagesUsers USING (PageId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Published = 1 ORDER BY PublishDate DESC;

-- SQL_GET_STATIC_PAGES_WITH_CATEGORY
SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed 
FROM StaticPages 
LEFT JOIN PageCategories USING (PageCategoryId)
WHERE PageCategoryId = ?;

-- SQL_GET_UNPUBLISHED_STATIC_PAGES
SELECT * FROM StaticPages WHERE Published = 0;

-- SQL_GET_UNPUBLISHED_STATIC_PAGES_WITH_AUTHOR
SELECT PageId, PageCategoryId, NavName, Title, PublishDate, TextArea, Published, Completed 
FROM StaticPages 
LEFT JOIN PagesUsers USING (PageId)
WHERE UserId = ?;

-- SQL_GET_PUBLISHED_STATIC_PAGES
SELECT * FROM StaticPages WHERE Published = 1;

-- SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_PAGES_IN_GROUP
SELECT * FROM StaticPages 
WHERE Published = 1 ORDER BY PostDate DESC LIMIT ?,?;



-- --------------------------------------
-- Queries for Post DAO
-- --------------------------------------


-- SQL_ADD_POST
INSERT INTO Posts (PostDate, ExpirationDate, Title, Synopsis, Body, Published)
VALUES (?, ?, ?, ?, ?, ?);

-- SQL_REMOVE_POST
DELETE FROM PostsTags WHERE PostId = ?; 
DELETE FROM PostsCategories WHERE PostId = ?; 
DELETE FROM PostsUsers WHERE PostId = ?; 
DELETE FROM PostsImages WHERE PostId = ?; 
DELETE FROM Posts WHERE PostId = ?;

-- SQL_UPDATE_POST
UPDATE Posts SET
PostDate = ?, 
ExpirationDate = ?, 
Title = ?, 
Synopsis = ?, 
Body = ?, 
Published = ?
WHERE PostId = ?;

-- SQL_GET_POST_BY_ID
SELECT * FROM Posts WHERE PostId = ?;

-- SQL_GET_ALL_POSTS
SELECT * FROM Posts;

-- SQL_GET_AUTHORS_OF_POST
SELECT UserId, UserName, Password, Role, FirstName, LastName, Email 
FROM Users 
LEFT JOIN PostsUsers USING (UserId) 
LEFT JOIN Posts USING (PostId) 
WHERE PostId = ?;

-- SQL_GET_POSTS_BY_USER
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsUsers USING (PostId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ?;

-- SQL_GET_POSTS_WITH_TAG
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsTags USING (PostId) 
LEFT JOIN Tags USING (TagId)
WHERE TagId = ?;

-- SQL_GET_IMAGES_BY_POST
SELECT Path 
FROM Images 
LEFT JOIN PostsImages USING (ImageId) 
LEFT JOIN Posts USING (PostId) 
WHERE PostId = ?;

-- SQL_GET_TAGS_FOR_POST
SELECT Tag 
FROM Tags 
LEFT JOIN PostsTags USING (TagId) 
LEFT JOIN Posts USING (PostId) 
WHERE PostId = ?;

-- SQL_GET_POSTS_WITH_CATEGORY
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsCategories USING (PostId) 
LEFT JOIN Categories USING (CategoryId) 
WHERE CategoryId = ? ORDER BY PostDate DESC LIMIT 10;

-- SQL_GET_CATEGORIES_OF_POST
SELECT Category 
FROM Categories
LEFT JOIN PostsCategories USING (CategoryId) 
LEFT JOIN Posts USING (PostId)
WHERE PostId = ?;

-- SQL_ADD_POST_IMAGES
INSERT INTO PostsImages (PostId, ImageId) 
VALUES (?, ?);

-- SQL_ADD_POST_TAGS
INSERT INTO PostsTags (PostId, TagId) 
VALUES (?, ?);

-- SQL_ADD_POST_USERS
INSERT INTO PostsUsers (PostId, UserId) 
VALUES (?, ?);

-- SQL_GET_UNPUBLISHED_POSTS
SELECT * FROM Posts 
WHERE Published = 0 ORDER BY PostDate DESC;

-- SQL_GET_PUBLISHED_POSTS
SELECT * FROM Posts 
WHERE Published = 1 ORDER BY PostDate DESC;

-- SQL_GET_LIMITED_NUMBER_OF_PUBLISHED_POSTS_IN_GROUP
SELECT * FROM Posts 
WHERE Published = 1 ORDER BY PostDate DESC LIMIT ?,?;

-- SQL_GET_COMPLETED_POSTS
SELECT * FROM Posts 
WHERE Completed = 1 ORDER BY PostDate DESC;

-- SQL_GET_INCOMPLETE_POSTS
SELECT * FROM Posts
WHERE Completed = 0 ORDER BY PostDate DESC;

-- SQL_GET_PUBLISHED_POSTS_BY_USER
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsUsers USING (PostId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Published = 1 ORDER BY PostDate DESC;

-- SQL_GET_UNPUBLISHED_POSTS_BY_USER
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsUsers USING (PostId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Published = 0 ORDER BY PostDate DESC;

-- SQL_GET_COMPLETED_POSTS_BY_USER
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsUsers USING (PostId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Completed = 1 ORDER BY PostDate DESC;

-- SQL_GET_INCOMPLETE_POSTS_BY_USER
SELECT PostId, PostDate, ExpirationDate, Title, Synopsis, Body, Published, Completed 
FROM Posts 
LEFT JOIN PostsUsers USING (PostId) 
LEFT JOIN Users USING (UserId) 
WHERE UserId = ? AND Completed = 0 ORDER BY PostDate DESC;

-- --------------------------------------
-- Queries for Comments DAO
-- --------------------------------------

-- SQL_GET_ALL_COMMENTS
SELECT * FROM Comments;

-- SQL_GET_COMMENT_BY_ID
SELECT * FROM Comments WHERE CommentId = ?;

-- SQL_ADD_COMMENT
INSERT INTO Comments (PostId, Comment) 
VALUES (?, ?);

-- SQL_DELETE_COMMENT
DELETE FROM Comments WHERE CommentId = ?;

-- SQL_EDIT_COMMENT
UPDATE Comments SET
PostId = ?, 
Comment = ?
WHERE CommentId = ?;

-- SQL_GET_COMMENTS_FOR_POST
SELECT * FROM Comments WHERE PostId = ?;


