package com.revature.beans.services;

import com.revature.beans.repositories.EventRepository;
import com.revature.beans.repositories.PostRepository;
import com.revature.models.Event;
import com.revature.models.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    private EventRepository repository;
    private PostRepository postRepository;

    public EventService(EventRepository eventRepository, PostRepository postRepository) {
        this.repository = eventRepository;
        this.postRepository = postRepository;
    }

    public Optional<Event> readByEventId(Integer eventId) {
        return this.repository.findById(eventId);
    }

    public List<Event> readByEventName(String name) {
        return this.repository.findByName(name);
    }

    public List<Event> readAll() {
        return this.repository.findAll();
    }

    public Event createEvent(Event event) {
        return this.repository.save(event);
    }

    public void deleteEvent(Integer eventId) {
        List<Post> postList = this.postRepository.findByEvent_EventId(eventId);
        for(Post post: postList) {
            if(this.postRepository.findById(post.getPostId()).isPresent()) {
                if(this.postRepository.findPostIfComment(post.getPostId()).isPresent()) {
                    this.postRepository.deleteComment(post.getPostId());
                    this.postRepository.deleteById(post.getPostId());
                }else {
                    this.postRepository.deleteById(post.getPostId());
                }
            }
        }
        this.repository.deleteById(eventId);
    }
}
