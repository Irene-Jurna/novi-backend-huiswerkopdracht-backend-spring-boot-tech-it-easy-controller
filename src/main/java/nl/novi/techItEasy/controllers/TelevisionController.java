package nl.novi.techItEasy.controllers;

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
    // Hetzelfde is return ResponseEntity.ok(body: "television"). Shortcut. (Builder pattern, zelf googlen)
    private List<Television> televisionList = new ArrayList<>();

    @GetMapping("")
    public ResponseEntity<List<Television>> getTelevisions() {
        return new ResponseEntity<>(televisionList, HttpStatus.OK);
    }

    // Hier zou indexoutofboundsexception logischer zijn?
    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevisionById(@PathVariable int id) {
        if (id >= televisionList.size()) {
            throw new RecordNotFoundException("This tv does not exist");
            //            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(televisionList.get(id), HttpStatus.OK);
        }
    }

    @PostMapping("")
    public ResponseEntity<Television> addTelevision(@RequestBody Television tv) {
        televisionList.add(tv);
        return new ResponseEntity<>(tv, HttpStatus.CREATED);
    }

    // In huiswerkles gebruiken ze hier @RequestParam. Waarom dit verschil?
    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable int id, @RequestBody Television tv) {
        if (id >= 0 && id > televisionList.size()) {
            televisionList.set(id, tv);
            return new ResponseEntity<>(tv, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    // Deze is op basis van naam, in bonusopdracht moet het op basis van id
    @DeleteMapping("")
    public ResponseEntity<Television> removeTelevision(@RequestParam String name, @RequestBody Television tv) {
        Television televisionToBeDeleted = null;
        for (Television t : televisionList) {
            if (t.name.equals(name)) {
                televisionToBeDeleted = t;
            }
        }
        if (televisionToBeDeleted == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            televisionList.remove(televisionToBeDeleted);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
