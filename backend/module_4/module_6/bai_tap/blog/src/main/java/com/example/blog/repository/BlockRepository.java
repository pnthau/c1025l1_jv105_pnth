package com.example.blog.repository;

import com.example.blog.entity.Blog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class BlockRepository implements IBlogRepository{
    @PersistenceContext
    private EntityManager entityManager;

    public List<Blog> findAll(){
        TypedQuery<Blog> blogTypedQuery= entityManager.createQuery("select b from Blog b", Blog.class);
        List<Blog> blogList = blogTypedQuery.getResultList();
        return blogList;
    }

    public Blog findById(int id)
    {
        Blog blog = entityManager.find(Blog.class,id);
        return blog;
    }


    public boolean save(Blog blog)
    {
        try{
            if(blog.getId() == 0)
            {
                entityManager.persist(blog);
            }else{
                entityManager.merge(blog);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean delete(int id)
    {
        Blog blog = entityManager.find(Blog.class, id);
        if (blog != null) {
            entityManager.remove(blog);
            return true;
        }
        return false;
    }
}
