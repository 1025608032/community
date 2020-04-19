package com.salon.community.service;

import com.salon.community.dto.PaginationDTO;
import com.salon.community.dto.ArticleDTO;
import com.salon.community.exception.CustomizeErrorCode;
import com.salon.community.exception.CustomizeException;
import com.salon.community.mapper.ArticleExtMapper;
import com.salon.community.mapper.ArticleMapper;
import com.salon.community.mapper.UserMapper;
import com.salon.community.model.Article;
import com.salon.community.model.ArticleExample;
import com.salon.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ArticleExtMapper articleExtMapper;

    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = (int) articleMapper.countByExample(new ArticleExample());

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }

        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);
        //size*(page-1)
        Integer offset = size * (page - 1);
        ArticleExample articleExample = new ArticleExample();
        articleExample.setOrderByClause("gmt_create desc");
        List<Article> articles = articleMapper.selectByExampleWithRowbounds(articleExample, new RowBounds(offset, size));
        List<ArticleDTO> articleDTOList = new ArrayList<>();
        for (Article article : articles) {
            User user = userMapper.selectByPrimaryKey(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setUser(user);
            articleDTOList.add(articleDTO);
        }
        paginationDTO.setData(articleDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        ArticleExample articleExample = new ArticleExample();

        articleExample.createCriteria()
                .andCreatorEqualTo(userId);
        Integer totalCount = (int) articleMapper.countByExample(articleExample);

        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }

        paginationDTO.setPagination(totalPage, page);

        //size*(page-1)
        Integer offset = size * (page - 1);
        ArticleExample example = new ArticleExample();
        example.createCriteria()
                .andCreatorEqualTo(userId);
        List<Article> articles = articleMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<ArticleDTO> articleDTOList = new ArrayList<>();

        for (Article article : articles) {
            User user = userMapper.selectByPrimaryKey(article.getCreator());
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(article, articleDTO);
            articleDTO.setUser(user);
            articleDTOList.add(articleDTO);
        }

        paginationDTO.setData(articleDTOList);
        return paginationDTO;
    }

    public ArticleDTO getById(Long id) {
        Article article = articleMapper.selectByPrimaryKey(id);
        if (article == null) {
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        ArticleDTO articleDTO = new ArticleDTO();
        BeanUtils.copyProperties(article, articleDTO);
        User user = userMapper.selectByPrimaryKey(article.getCreator());
        articleDTO.setUser(user);
        return articleDTO;
    }

    public void createOrUpdate(Article article) {
        if (article.getId() == null) {
            // 创建
            article.setGmtCreate(System.currentTimeMillis());
            article.setGmtModified(article.getGmtCreate());
            article.setViewCount(0);
            article.setLikeCount(0);
            article.setCommentCount(0);
            articleMapper.insert(article);
        } else {
            // 更新
            Article updateArticle = new Article();
            updateArticle.setGmtModified(System.currentTimeMillis());
            updateArticle.setTitle(article.getTitle());
            updateArticle.setDescription(article.getDescription());
            updateArticle.setTag(article.getTag());
            ArticleExample example = new ArticleExample();
            example.createCriteria()
                    .andIdEqualTo(article.getId());
            int updated = articleMapper.updateByExampleSelective(updateArticle, example);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Article article = new Article();
        article.setId(id);
        article.setViewCount(1);
        articleExtMapper.incView(article);
    }

    public List<ArticleDTO> selectRelated(ArticleDTO queryDTO) {
        if (StringUtils.isBlank(queryDTO.getTag())) {
            return new ArrayList<>();
        }
        String[] tags = StringUtils.split(queryDTO.getTag(), ",");
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));
        Article article = new Article();
        article.setId(queryDTO.getId());
        article.setTag(regexpTag);

        List<Article> articles = articleExtMapper.selectRelated(article);
        List<ArticleDTO> articleDTOS = articles.stream().map(q -> {
            ArticleDTO articleDTO = new ArticleDTO();
            BeanUtils.copyProperties(q, articleDTO);
            return articleDTO;
        }).collect(Collectors.toList());
        return articleDTOS;
    }
}
