import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton,
  DateField,
  DeleteButton
} from '@refinedev/antd'
import { useMany } from '@refinedev/core';
import { Space, Table } from 'antd';
import dayjs from 'dayjs';


export const ListCoupon = () => {
  const {
    tableProps,
    sorters,
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
    pagination: {
      pageSize: 20
    }
  })

  // const {data} = useMany({
  //   resource: "coupon_type",
  //   ids: tableProps?.dataSource?.map(coupon => coupon.coupon_type_id) ?? []
  // })

  return (
    <List>
      <Table {...tableProps} rowKey="id">
        <Table.Column
          dataIndex="id"
          title="ID"
          sorter
          defaultSortOrder={getDefaultSortOrder("id", sorters)}
        />
        <Table.Column dataIndex="code" title="Code" />
        <Table.Column dataIndex="discountAmount" title="Discount Amount" />
        <Table.Column dataIndex="usageLimit" title="Usage Limit" />
        <Table.Column dataIndex="usedCount" title="Usage Count" />
        <Table.Column
          title="Start Date"
          render={(_, record)=> (
              <DateField value={dayjs(record.startDate).toString()} />
            )}
          />
        <Table.Column
          title="End Date"
          render={(_, record)=> (
              <DateField value={dayjs(record.endDate).toString()} />
            )}
          />
        <Table.Column dataIndex="status" title="Status" />
        {/* <Table.Column title="Coupon Type" */}
        {/*   render={(_, record)=>( */}
        {/*     <span>{data?.data?.find(type => type.id == record.couponTypeId)?.type ?? ""}</span> */}
        {/*   )} */}
        {/* /> */}
        <Table.Column
          title="Actions"
          render={(_, record) => (
            <Space>
              <ShowButton hideText size="small" recordItemId={record.id} />
              <EditButton hideText size="small" recordItemId={record.id} />
              <DeleteButton hideText size="small" recordItemId={record.id} />
            </Space>
          )}
        />
      </Table>
    </List>
  );
};
