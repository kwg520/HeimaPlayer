package com.example.heimaplayer.model

class YuedanBean {

    /**
     * error : false
     * logined : false
     * message :
     * playlists : [{"description":"","id":995,"image":"//img1.c.yinyuetai.com/others/frontPageRec/190413/0/-M-bf32a132108168b309414b3897eaaf78_0x0.jpg","person":{"headImg":"//img1.c.yinyuetai.com/uploads/bnDHzVCsS5.jpg","userId":27173913,"userName":"雨润我心","vipLevel":0},"playListId":4056215,"tags":["动漫","收藏","清新"],"title":"四月是你的谎言","totalComments":"343","totalViews":"21万","url":"http://v.yinyuetai.com/playlist/4056215?f=SY-JPYD-list-1","videoNum":20},{"description":"","id":994,"image":"//img3.c.yinyuetai.com/others/frontPageRec/190412/0/-M-fcbc1aeb6a8e4ecd3fd4af2e7747524c_0x0.jpg","person":{"headImg":"//img1.c.yinyuetai.com/user/avatar/150605/10808350/-M-92b4d92eb90b585589939a933b64d755_50x50.jpg","userId":10808350,"userName":"sixgod","vipLevel":0},"playListId":3215892,"title":"芳华绝代张国荣","totalComments":"6","totalViews":"2.0万","url":"http://v.yinyuetai.com/playlist/3215892?f=SY-JPYD-list-2","videoNum":38},{"description":"魅力女团!<br></br><br></br><br></br>性感来袭!","id":993,"image":"//img1.c.yinyuetai.com/others/frontPageRec/190411/0/-M-7870817f850c9ae0924aca63c4944b40_0x0.jpg","person":{"headImg":"//img2.c.yinyuetai.com/uploads/persons/akn/5472103/44930138D10E9BE25532E08C6A6F0534.JPG","userId":5472103,"userName":"潮爆乐坛","vipLevel":0},"playListId":3162800,"tags":["性感","动感","节奏","舞曲","sexy","女团","韩国","日本"],"title":"鼻血止不住! 就是要性感~","totalComments":"19","totalViews":"5.0万","url":"http://v.yinyuetai.com/playlist/3162800?f=SY-JPYD-list-3","videoNum":80},{"description":"Pentatonix,来自美国德克萨斯州阿灵顿的纯人声伴奏乐团,The Sing-Off第三季的优胜者,由四名男生和一名女生成员组成。该乐队以完美的默契度所演唱的和声,足以媲美乐队伴奏的乐曲。同时,该组合的音乐l领域极为丰富,包括摇滚乐、电子乐、雷鬼音乐、乡村音乐、朋克等,每一种风格的音乐作品在他们的演唱之下都富含新颖的内涵,使人产生耳目一新的感觉。作为发片艺人,PTX目前已经发行两部以翻唱歌曲为主打的专辑,刚发布在美国公告板排行上名列前茅,备受期待的第三张唱片目前正在与酝酿中。PTX正在进行全美的巡回演唱会,继续弘扬阿卡贝拉这种独特的音乐文化。","id":992,"image":"//img0.c.yinyuetai.com/others/frontPageRec/190410/0/-M-a63b05c7ec9ed3404617e0a85f764cb3_0x0.jpg","person":{"headImg":"//img4.c.yinyuetai.com/user/avatar/140116/15641173/E2EC01439881B4329713B3761155E6CF_50x50.jpeg","userId":15641173,"userName":"夏季音符","vipLevel":0},"playListId":1933820,"tags":["纯人声伴奏乐团","Pentatonix","动感","翻唱"],"title":"Pentatonix纯人声伴奏乐团","totalComments":"3","totalViews":"9524","url":"http://v.yinyuetai.com/playlist/1933820?f=SY-JPYD-list-4","videoNum":44},{"description":"早几年,我也买过吉他,可是却忘记把那梦想也买下。<br></br>那时候,我也曾看过一个小孩还有他当时努力的样子。<br></br>而现在呢,怎么说,我心爱的气球早就飞走了,还剩下那蒙尘的木吉他。<br></br>我不喝酒不抽烟,还有听歌算不算得上也是一种理想。<br></br>不怪我,这些歌真让我有点醉。","id":991,"image":"//img4.c.yinyuetai.com/others/frontPageRec/190408/0/-M-1fdb9eedb93a19283e22687c9c0a0487_0x0.jpg","person":{"headImg":"//img3.c.yinyuetai.com/user/avatar/141126/4886138/-M-fcf859fbb00fa010a8daec319e88f74c_50x50.jpg","userId":4886138,"userName":"ArmastusIse","vipLevel":0},"playListId":3840354,"title":"不怪我,这些歌真让我有点醉。","totalComments":"210","totalViews":"48万","url":"http://v.yinyuetai.com/playlist/3840354?f=SY-JPYD-list-5","videoNum":20},{"description":"","id":990,"image":"//img1.c.yinyuetai.com/others/frontPageRec/190407/0/-M-06ac9106814105b58aa05b530f128b5e_0x0.jpg","person":{"headImg":"//img1.c.yinyuetai.com/uploads/1JZO89a5s.jpg","userId":56768711,"userName":"手机用户56768711","vipLevel":0},"playListId":4991171,"title":"美妙的巴洛克音乐","totalComments":"3","totalViews":"1.0万","url":"http://v.yinyuetai.com/playlist/4991171?f=SY-JPYD-list-6","videoNum":80}]
     */

    var isError: Boolean = false
    var isLogined: Boolean = false
    var message: String? = null
    var playlists: List<PlaylistsBean>? = null

    class PlaylistsBean {
        /**
         * description :
         * id : 995
         * image : //img1.c.yinyuetai.com/others/frontPageRec/190413/0/-M-bf32a132108168b309414b3897eaaf78_0x0.jpg
         * person : {"headImg":"//img1.c.yinyuetai.com/uploads/bnDHzVCsS5.jpg","userId":27173913,"userName":"雨润我心","vipLevel":0}
         * playListId : 4056215
         * tags : ["动漫","收藏","清新"]
         * title : 四月是你的谎言
         * totalComments : 343
         * totalViews : 21万
         * url : http://v.yinyuetai.com/playlist/4056215?f=SY-JPYD-list-1
         * videoNum : 20
         */

        var description: String? = null
        var id: Int = 0
        var image: String? = null
        var person: PersonBean? = null
        var playListId: Int = 0
        var title: String? = null
        var totalComments: String? = null
        var totalViews: String? = null
        var url: String? = null
        var videoNum: Int = 0
        var tags: List<String>? = null

        class PersonBean {
            /**
             * headImg : //img1.c.yinyuetai.com/uploads/bnDHzVCsS5.jpg
             * userId : 27173913
             * userName : 雨润我心
             * vipLevel : 0
             */

            var headImg: String? = null
            var userId: Int = 0
            var userName: String? = null
            var vipLevel: Int = 0
        }
    }
}
