package nl.novi.techItEasy.controllers;

import nl.novi.techItEasy.exceptions.IndexOutOfBoundsException;
import nl.novi.techItEasy.exceptions.InvalidNameException;
import nl.novi.techItEasy.exceptions.RecordNotFoundException;
import nl.novi.techItEasy.models.Television;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("televisions")
public class TelevisionController {
    private List<Television> televisionList = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<List<Television>> getTelevisions() {
        // Een andere return kan zijn: ResponseEntity.ok(body: "television")
        return new ResponseEntity<>(televisionList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        if (id >= televisionList.size()) {
            // De 'throw' komt in plaats van: return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            throw new IndexOutOfBoundsException("This tv does not exist");
        } else {
            return new ResponseEntity<>(televisionList.get(id), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Television> addTelevision(@RequestBody Television tv) {
        if (tv.name.length() > 20) {
            throw new InvalidNameException("Tv name too long. Please fill in a name under 20 characters.");
        }
        televisionList.add(tv);
        return new ResponseEntity<>(tv, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television tv) {
        if (id >= 0 && id > televisionList.size()) {
            televisionList.set(id, tv);
            return new ResponseEntity<>(tv, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Television> removeTelevisionBasedOnId(@PathVariable int id) {
        televisionList.remove(id);
        return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    // Extra: DELETE request op basis van String name
    @DeleteMapping("")
    public ResponseEntity<Television> removeTelevision(@RequestParam String name, @RequestBody Television tv) {
        Television televisionToBeDeleted = null;
        for (Television t : televisionList) {
            if (t.name.equals(name)) {
                televisionToBeDeleted = t;
            }
        }
        if (televisionToBeDeleted == null) {
            throw new RecordNotFoundException("We cannot find the tv you'd like to delete");
        } else {
            televisionList.remove(televisionToBeDeleted);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
