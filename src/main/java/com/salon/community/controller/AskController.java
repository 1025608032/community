package com.salon.community.controller;

import com.salon.community.cache.TagCache;
import com.salon.community.dto.CommentDTO;
import com.salon.community.dto.PaginationDTO;
import com.salon.community.dto.QuestionDTO;
import com.salon.community.enums.CommentTypeEnum;
import com.salon.community.model.Question;
import com.salon.community.model.User;
import com.salon.community.service.CommentService;
import com.salon.community.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AskController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/ask")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "5") Integer size
                        ) {
        PaginationDTO pagination = questionService.list(page, size);
        model.addAttribute("pagination", pagination);
        model.addAttribute("tags", TagCache.get());
        return "ask";
    }

    @GetMapping("/ask/{id}")
    public String question(@PathVariable(name = "id") Long id, Model model) {
        QuestionDTO question = questionService.getById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(question);
        List<CommentDTO> comments = commentService.listByTargetId(id, CommentTypeEnum.QUESTION);
        //累加阅读数
        questionService.incView(id);
        model.addAttribute("question", question);
        model.addAttribute("comments", comments);
        model.addAttribute("relatedQuestions", relatedQuestions);
        model.addAttribute("tags", TagCache.get());
        return "ask";
    }

    @PostMapping("/ask/{id}")
    public String doQuestionEdit(@PathVariable(name = "id") Long id,
                                 @RequestParam(value = "Etitle", required = false) String title,
                                 @RequestParam(value = "Edescription", required = false) String description,
                                 @RequestParam(value = "Etag", required = false) String tag,
                                 HttpServletRequest request,
                                 Model model) {
        model.addAttribute("Etitle", title);
        model.addAttribute("Edescription", description);
        model.addAttribute("Etag", tag);

        QuestionDTO questionDTO = questionService.getById(id);
        model.addAttribute("question", questionDTO);

        if (title == null || title == "") {
            model.addAttribute("Eerror1", "标题不能为空");
            return "ask";
        }
        if (tag == null || tag == "") {
            model.addAttribute("Eerror1", "标签不能为空");
            return "ask";
        }
        if (description == null || description == "") {
            model.addAttribute("Eerror1", "问题描述不能为空");
            return "ask";
        }
        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("Eerror1", "输入非法标签:" + invalid);
            return "ask";
        }

        User user = (User) request.getSession().getAttribute("user");

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setId(id);
        question.setCreator(user.getId());
        questionService.createOrUpdate(question);
        return "redirect:/ask/{id}";
    }
}


