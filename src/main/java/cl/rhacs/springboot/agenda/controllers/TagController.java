package cl.rhacs.springboot.agenda.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.rhacs.springboot.agenda.models.Tag;
import cl.rhacs.springboot.agenda.repositories.TagRepository;

@RestController
@RequestMapping(path = "/tags")
public class TagController {

    // Attributes
    // -----------------------------------------------------------------------------------------

    @Autowired
    private TagRepository tagRepository;

    // Get Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Get a list of existing {@link Tag}s
     *
     * @return
     */
    @GetMapping(path = { "", "/" })
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> tags = tagRepository.findAll();

        if (!tags.isEmpty())
            return new ResponseEntity<>(tags, HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Get the details of an existing {@link Tag}
     *
     * @param id the tag's id
     * @return the details of the tag
     */
    @GetMapping(path = { "/{id:^\\d+$}" })
    public ResponseEntity<Tag> getTag(@PathVariable Long id) {
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isPresent())
            return new ResponseEntity<>(tag.get(), HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Post Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Add a new {@link Tag}
     *
     * @param tag the tag information
     * @return the saved tag
     */
    @PostMapping(path = { "", "/" })
    public ResponseEntity<Tag> addTag(@RequestBody Tag tag) {
        Tag savedTag = tagRepository.save(tag);
        return new ResponseEntity<>(savedTag, HttpStatus.OK);
    }

    // Put Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Edit an existing {@link Tag}
     *
     * @param id  the tag id
     * @param tag the tag information
     * @return the modified tag
     */
    @PutMapping(path = { "/{id:^\\d+$}" })
    public ResponseEntity<Tag> updateTag(@PathVariable Long id, @RequestBody Tag tag) {
        Optional<Tag> foundTag = tagRepository.findById(id);

        if (foundTag.isPresent()) {
            if(tag.getId() != null && (foundTag.get().getId() == tag.getId())) {
                Tag updatedTag = tagRepository.save(tag);
                return new ResponseEntity<>(updatedTag, HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Delete Mappings
    // -----------------------------------------------------------------------------------------

    /**
     * Delete an existing {@link Tag}
     *
     * @param id the tag id
     * @return OK status if deletion was succesfull, NOT_FOUND if given id doesn't
     *         exists
     */
    @DeleteMapping(path = { "/{id:^\\d+$}" })
    public ResponseEntity<Tag> deleteTag(@PathVariable Long id) {
        Optional<Tag> tag = tagRepository.findById(id);

        if (tag.isPresent()) {
            tagRepository.delete(tag.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
