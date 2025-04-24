import { useShow } from "@refinedev/core";
import { TextField, Show } from "@refinedev/antd";

import { Typography } from "antd";

export const ShowPlatform = () => {
  const {
    query: { data, isLoading },
  } = useShow();

  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

      <Typography.Title level={5}>Name</Typography.Title>
      <TextField value={data?.data?.name} />

      <Typography.Title level={5}>Title</Typography.Title>
      <TextField value={data?.data?.title} />

      <Typography.Title level={5}>Parent title</Typography.Title>
      <TextField value={data?.data?.parent?.title} />

    </Show>
  );
};
