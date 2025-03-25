import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton
} from '@refinedev/antd'
import { Space, Table } from 'antd';


export const ListCoupon = () => {
  const {
    tableProps,
    sorters,
  } = useTable({
    sorters: {initial: [{field: "id", order: "asc"}]},
    syncWithLocation: true,
  })

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
        <Table.Column dataIndex="discount_amount" title="Discount Amount" />
        <Table.Column dataIndex="usage_limit" title="Usage Limit" />
        <Table.Column dataIndex="usage_count" title="Usage Count" />
        <Table.Column dataIndex="start_date" title="Start Date" />
        <Table.Column dataIndex="end_date" title="End Date" />
        <Table.Column dataIndex="status" title="Status" />
        <Table.Column dataIndex="type_id" title="Coupon Type" />
        <Table.Column
          title="Actions"
          render={(_, record) => (
            <Space>
              <ShowButton hideText size="small" recordItemId={record.id} />
              <EditButton hideText size="small" recordItemId={record.id} />
            </Space>
          )}
        />
      </Table>
    </List>
  );
};
