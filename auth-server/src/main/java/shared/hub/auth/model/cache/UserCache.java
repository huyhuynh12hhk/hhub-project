package shared.hub.auth.model.cache;


import lombok.Data;
import shared.hub.auth.model.entity.AppUser;

@Data
public class UserCache {
    private Long version;
    private AppUser user;

    public UserCache cloneFrom(AppUser user) {
        this.user = user;
        return this;
    }

    public UserCache withVersion(Long version) {
        this.version = version;
        return this;
    }
}