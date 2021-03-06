package com.softmiracle.githubmvp.data.interactor;

import com.softmiracle.githubmvp.data.models.Repo;

import java.util.List;

/**
 * Created by dnsfrolov on 24.04.2017.
 */

public interface RepoInteractor {

    void getRepoList(String user, int page, InteractorCallback<List<Repo>> callback);

    void getUserStarredList(String user, int page, InteractorCallback<List<Repo>> callback);
}
