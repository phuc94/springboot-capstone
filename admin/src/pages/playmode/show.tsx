import { useShow } from "@refinedev/core";
import { TextField, MarkdownField, Show } from "@refinedev/antd";

import { Typography } from "antd";

export const ShowPlaymode = () => {
  const {
    query: { data, isLoading },
  } = useShow();

  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

    <Typography.Title level={5}>Name</Typography.Title>
      <MarkdownField value={data?.data?.name} />
    </Show>
  );
};
