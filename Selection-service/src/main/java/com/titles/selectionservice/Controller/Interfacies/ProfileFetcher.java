package com.titles.selectionservice.Controller.Interfacies;

import com.titles.selectionservice.Model.Profile;

import java.util.Optional;
import java.util.Set;

public interface ProfileFetcher {
    Optional<Profile> fetch(Integer userId);
    Set<Profile> fetch();
}
