import { useShow } from "@refinedev/core";
import { TextField, Show } from "@refinedev/antd";

import { Switch, Typography } from "antd";

export const ShowMedia = () => {
  const {
    query: { data, isLoading },
  } = useShow();

  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

      <Typography.Title level={5}>Url</Typography.Title>
      <TextField value={data?.data?.url} />

      <Typography.Title level={5}>Title</Typography.Title>
      <TextField value={data?.data?.title} />

      <Typography.Title level={5}>Media Type</Typography.Title>
      <TextField value={data?.data?.media_type} />

      <Typography.Title level={5}>Is Primary</Typography.Title>
      <Switch value={data?.data?.primary} disabled />
    </Show>
  );
};
