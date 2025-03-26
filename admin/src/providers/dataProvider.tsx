import type { BaseKey, DataProvider } from "@refinedev/core";

const API_URL = "http://localhost:8080/api";

const fetcher = async (url: string, options?: RequestInit) => {
  return fetch(url, {
    ...options,
    headers: {
      ...options?.headers,
      Authorization: localStorage.getItem("my_access_token") as string,
    },
  });
};

export const dataProvider: DataProvider = {
  getOne: async ({ resource, id }) => {
    const response = await fetcher(`${API_URL}/${resource}/${id}`);

    if (response.status < 200 || response.status > 299) throw response;

    const data = await response.json();

    return { data: data.data };
  },
  update: async ({resource, id, variables}) => {
    const res = await fetcher(`${API_URL}/${resource}/${id}`,{
      method: "POST",
      body: JSON.stringify(variables),
      headers: {
        "Content-Type" : "application/json"
      }
    })

    if (res.status < 200 || res.status > 299) throw res;

    const data = await res.json();

    return {data: data.data};

  },
  getList: async ({
    resource,
    // pagination,
    // filters,
    // sorters,
    // meta
  }) => {
    const params = new URLSearchParams();

    // if (pagination) {
    //   console.log(pagination)
    //   params.append("_start", (pagination?.current - 1) * pagination?.pageSize);
    //   params.append("_end", pagination?.current * pagination?.pageSize);
    // }

    // if (sorters && sorters.length > 0) {
    //   params.append("_sort", sorters.map(sorter => sorter.field).join(","));
    //   params.append("_order", sorters.map(sorter => sorter.order).join(","));
    // }

    // if (filters && filters.length > 0) {
    //   filters.forEach(filter => {
    //     if ("field" in filter && filter.operator == "eq") {
    //       params.append(filter.field, filter.value)
    //     }
    //   })
    // }

    const response = await fetcher(`${API_URL}/${resource}?${params.toString()}`);

    if (response.status < 200 || response.status > 299) throw response;

    const data = await response.json();
    console.log(data)

    // const total = Number(response.headers.get("x-total-count"));

    return {
      data: data.data,
      // total
    };
  },
  create: async ({ resource, variables }) => {
    const response = await fetcher(`${API_URL}/${resource}`, {
      method: "POST",
      body: JSON.stringify(variables),
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (response.status < 200 || response.status > 299) throw response;

    const data = await response.json();

    return { data: data.data };
  },
  getMany: async ({ resource, ids }) => {
    const params = new URLSearchParams();
    if (ids) {
      ids.forEach((id) => params.append("id", id as string));
    }

    const res = await fetcher(`${API_URL}/${resource}?${params.toString()}`);

    if (res.status < 200 || res.status > 299) throw res;

    const data = await res.json();

    return {data: data.data}

  },
  deleteOne: () => {
    throw new Error("Not implemented");
  },
  getApiUrl: () => API_URL,
  // Optional methods:
  // createMany: () => { /* ... */ },
  // deleteMany: () => { /* ... */ },
  // updateMany: () => { /* ... */ },
  // custom: () => { /* ... */ },
};
