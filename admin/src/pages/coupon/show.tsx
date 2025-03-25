import { useShow } from "@refinedev/core";
import { TextField, Show, DateField, NumberField, TagField } from "@refinedev/antd";

import { Switch, Typography } from "antd";

export const ShowCoupon = () => {
  const {
    query: { data, isLoading },
  } = useShow();

  return (
    <Show isLoading={isLoading}>
      <Typography.Title level={5}>Id</Typography.Title>
      <TextField value={data?.data?.id} />

      <Typography.Title level={5}>Code</Typography.Title>
      <TextField value={data?.data?.code} />

      <Typography.Title level={5}>Discount Amount</Typography.Title>
      <NumberField value={data?.data?.discount_amount} />

      <Typography.Title level={5}>Usage Limit</Typography.Title>
      <NumberField value={data?.data?.usage_limit} />
      
      <Typography.Title level={5}>Usage Count</Typography.Title>
      <NumberField value={data?.data?.usage_count} />

      <Typography.Title level={5}>Start Date</Typography.Title>
      <DateField value={data?.data?.start_date} />

      <Typography.Title level={5}>End Date</Typography.Title>
      <DateField value={data?.data?.end_date} />
      
      <Typography.Title level={5}>Status</Typography.Title>
      <TagField value={data?.data?.status} />

      <Typography.Title level={5}>Coupon Type</Typography.Title>
      <TextField value={data?.data?.type_id} />
    </Show>
  );
};
