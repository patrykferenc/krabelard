import axios, { AxiosInstance, AxiosResponse } from 'axios';

export const krapi = (url: string): AxiosInstance => {
  let client: AxiosInstance = axios.create({
    baseURL: url,
    headers: {
      'Content-Type': 'application/json',
      Accept: 'application/json'
    }
  });

  client.interceptors.response.use(
    (response: AxiosResponse) => {
      console.log('hello world');
      return response;
    },
    function (error: any) {
      console.log('An error occurred while calling krapi', error);
      if (error.response.status === 404) {
        return { status: error.response.status };
      }
      return Promise.reject(error.response);
    }
  );
  return client;
};
