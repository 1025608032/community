package com.salon.community.cache;

import com.salon.community.dto.TagDTO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TagCache {
    public static List<TagDTO> get() {
        List<TagDTO> tagDTOS = new ArrayList<>();
        TagDTO history = new TagDTO();
        history.setCategoryName("历史");
        history.setTags(Arrays.asList("中国古代史", "中国近代史", "民国", "抗日战争", "解放战争", "改革开放", "世界史",
                "文艺复兴","宗教改革", "大航海时代", "大革命时代", "一战", "二战", "历史人物","科技史"));
        tagDTOS.add(history);

        TagDTO culture = new TagDTO();
        culture.setCategoryName("文化");
        culture.setTags(Arrays.asList("世界观", "人生观", "价值观", "文学", "绘画", "音乐", "舞蹈", "雕塑", "戏剧",
                "建筑", "电影", "游戏", "现代文化", "中华文化", "西方文化", "日本文化", "风土人情"));
        tagDTOS.add(culture);

        TagDTO society = new TagDTO();
        society.setCategoryName("社会");
        society.setTags(Arrays.asList("热点事件", "经济", "政治", "教育", "法律", "意识形态", "社会主义", "资本主义", "封建社会",
                "奴隶社会", "原始社会", "共产主义", "国际局势", "社会发展"));
        tagDTOS.add(society);

        TagDTO philosophy = new TagDTO();
        philosophy.setCategoryName("哲学");
        philosophy.setTags(Arrays.asList("唯心主义", "唯物主义", "认识论", "存在论", "伦理学", "诸子百家", "儒家","道家",
                "法家", "禅宗","心学","古希腊","哲学家","哲学流派"));
        tagDTOS.add(philosophy);

        TagDTO religion = new TagDTO();
        religion.setCategoryName("宗教");
        religion.setTags(Arrays.asList("新教", "天主教", "东正教", "伊斯兰教", "佛教", "古希腊神话", "道教", "祆教", "犹太教",
                "神道教","北欧神话","信仰","上帝","天职"));
        tagDTOS.add(religion);

        return tagDTOS;
    }

    public static String filterInvalid(String tags) {
        String[] split = StringUtils.split(tags, ",");
        List<TagDTO> tagDTOS = get();

        List<String> tagList = tagDTOS.stream().flatMap(tag -> tag.getTags().stream()).collect(Collectors.toList());
        String invalid = Arrays.stream(split).filter(t -> !tagList.contains(t)).collect(Collectors.joining(","));
        return invalid;
    }
}
