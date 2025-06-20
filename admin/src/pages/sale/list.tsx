import {
  getDefaultSortOrder,
  useTable,
  List,
  ShowButton,
  EditButton,
  DateField
} from '@refinedev/antd'
import { Space, Table } from 'antd';
import dayjs from 'dayjs';


export const ListSale = () => {
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

  return (
    <List>
      <Table {...tableProps} rowKey="id" >
        <Table.Column
          dataIndex="id"
          title="ID"
          sorter
          defaultSortOrder={getDefaultSortOrder("id", sorters)}
        />
        <Table.Column dataIndex="amount" title="Amount" />
        <Table.Column dataIndex="status" title="Status" />
        <Table.Column title="Start Date"
          render={(_, record) => (
              <DateField value={dayjs(record.startDate).toString()} />
          )}
        />
        <Table.Column title="End Date"
          render={(_, record) => (
              <DateField value={dayjs(record.endDate).toString()} />
          )}
        />
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
