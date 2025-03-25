import { useShow } from "@refinedev/core";
import { TextField, MarkdownField, Show } from "@refinedev/antd";

import { Typography } from "antd";

export const ShowSale = () => {
  const {
    query: { data, isLoading },
  } = useShow();
console.log(data?.data?.amount)
  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

      <Typography.Title level={5}>Amount</Typography.Title>
      <TextField value={data?.data?.amount} />

      <Typography.Title level={5}>Status</Typography.Title>
      <MarkdownField value={data?.data?.status} />
    </Show>
  );
};
