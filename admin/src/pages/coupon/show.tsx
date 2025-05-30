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
      <NumberField value={data?.data?.discountAmount} />

      <Typography.Title level={5}>Usage Limit</Typography.Title>
      <NumberField value={data?.data?.usageLimit} />
      
      <Typography.Title level={5}>Used Count</Typography.Title>
      <NumberField value={data?.data?.usedCount} />

      <Typography.Title level={5}>Start Date</Typography.Title>
      <DateField value={data?.data?.startDate} />

      <Typography.Title level={5}>End Date</Typography.Title>
      <DateField value={data?.data?.endDate} />
      
      <Typography.Title level={5}>Status</Typography.Title>
      <TagField value={data?.data?.status} />

      <Typography.Title level={5}>Coupon Type</Typography.Title>
      <TextField value={data?.data?.couponTypeId} />
    </Show>
  );
};
