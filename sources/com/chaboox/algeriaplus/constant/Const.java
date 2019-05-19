package com.chaboox.algeriaplus.constant;

public class Const {
    public static final String get_all_posts_of_category_url = "http://algeriaplus.com/index.php/wp-json/wp/v2/posts?categories=CATEGORY_ID&fields=id,date,link,title,better_featured_image";
    public static final String get_categories_url = "http://algeriaplus.com/index.php/wp-json/wp/v2/categories?fields=id,description,name";
    public static final String get_content_by_id = "http://algeriaplus.com/index.php/wp-json/wp/v2/posts/POST_ID?fields=content";
    public static final String pages = "http://algeriaplus.com/index.php/wp-json/wp/v2/pages/PAGE_ID?fields=content";
    public static final String url = "http://algeriaplus.com/index.php/wp-json/wp/v2/posts/?filter[category_name]=android&per_page=12&fields=id,date,link,title,better_featured_image,categories";
    public static final String url2 = "http://algeriaplus.com/index.php/wp-json/wp/v2/posts/?filter[category_name]=android&per_page=11&fields=id,categories";
}
