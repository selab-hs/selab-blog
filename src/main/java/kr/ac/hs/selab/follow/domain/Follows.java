package kr.ac.hs.selab.follow.domain;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.TreeSet;

@Embeddable
@EqualsAndHashCode
public class Follows {

    @OneToMany(mappedBy = "fromMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> fromFollows = new TreeSet<>();

    @OneToMany(mappedBy = "toMember", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Follow> toFollows = new TreeSet<>();

    public void addFromFollows(Follow follow) {
        fromFollows.add(follow);
    }

    public void addToFollows(Follow follow) {
        toFollows.add(follow);
    }

    public void removeFromFollows(Follow follow) {
        fromFollows.remove(follow);
    }

    public void removeToFollows(Follow follow) {
        toFollows.remove(follow);
    }

    public boolean existsFromFollows(Follow follow) {
        if (fromFollows.contains(follow)) {
            return true;
        }
        return false;
    }

    public boolean existsToFollows(Follow follow) {
        if (toFollows.contains(follow)) {
            return true;
        }
        return false;
    }

    protected Follows() {

    }
}