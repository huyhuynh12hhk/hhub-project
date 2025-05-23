﻿using blog_api.DTOs;
using blog_api.DTOs.Response;

namespace blog_api.Repositories.Http
{
    public interface IFollowRepository
    {
        Task<BaseRepsonse<List<FollowResponse>>?> GetFollowersByUIDAsync(string accessToken, string uid);
        Task<BaseRepsonse<List<FollowResponse>>?> GetFollingsByUIDAsync(string accessToken, string uid);
    }
}
